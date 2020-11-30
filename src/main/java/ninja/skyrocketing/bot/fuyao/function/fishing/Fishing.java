package ninja.skyrocketing.bot.fuyao.function.fishing;

import lombok.NoArgsConstructor;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import ninja.skyrocketing.bot.fuyao.pojo.bot.BotGameFishing;
import ninja.skyrocketing.bot.fuyao.pojo.group.*;
import ninja.skyrocketing.bot.fuyao.service.bot.BotGameFishingService;
import ninja.skyrocketing.bot.fuyao.service.group.GroupCoinService;
import ninja.skyrocketing.bot.fuyao.service.group.GroupExpService;
import ninja.skyrocketing.bot.fuyao.service.group.GroupFishingService;
import ninja.skyrocketing.bot.fuyao.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @Author skyrocketing Hong
 * @Date 2020-11-29 13:14:45
 */
@Component
@NoArgsConstructor
public class Fishing {
    private static BotGameFishingService botGameFishingService;
    private static GroupFishingService groupFishingService;
    private static GroupCoinService groupCoinService;
    private static GroupExpService groupExpService;
    @Autowired
    private Fishing(
            BotGameFishingService botGameFishingService,
            GroupFishingService groupFishingService,
            GroupCoinService groupCoinService,
            GroupExpService groupExpService
    ) {
        Fishing.botGameFishingService = botGameFishingService;
        Fishing.groupFishingService = groupFishingService;
        Fishing.groupCoinService = groupCoinService;
        Fishing.groupExpService = groupExpService;
    }

    //根据经验值和金币值钓鱼
    public static Message FishByExpAndCoin(GroupMessage groupMessage) {
        MessageChainBuilder messageChainBuilder = new MessageChainBuilder();
        GroupCoin groupCoin = groupCoinService.GetCoinByGroupUser(groupMessage.getGroupUser());
        GroupExp groupExp = groupExpService.GetExpByGroupUser(groupMessage.getGroupUser());
        if (groupCoin == null && groupExp == null) {
            messageChainBuilder.add("❌ 从未签到和领金币");
        } else {
            if (groupCoin == null) {
                messageChainBuilder.add("❌ 从未领金币");
                return messageChainBuilder.asMessageChain();
            } else if (groupExp == null) {
                messageChainBuilder.add("❌ 从未签到");
                return messageChainBuilder.asMessageChain();
            } else {
                if (groupCoin.getCoin() < 50) {
                    if (groupCoin.getCoin() < 10) {
                        messageChainBuilder.add("❌ 金币小于 10，不够一次钓鱼" + "\n" + "请先领金币");
                        return messageChainBuilder.asMessageChain();
                    }
                    messageChainBuilder.add("❌ 金币小于 50" + "\n" + "请先领金币");
                    return messageChainBuilder.asMessageChain();
                } else if (groupExp.getExp() < 15) {
                    messageChainBuilder.add("❌ 经验值小于 15" + "\n" + "请先签到");
                    return messageChainBuilder.asMessageChain();
                }
            }
            return FishAFish(groupMessage, groupCoin);
        }
        return messageChainBuilder.asMessageChain();
    }

    //直接获取一条鱼
    public static BotGameFishing GetFish(GroupMessage groupMessage) {
        List<BotGameFishing> allFish = botGameFishingService.GetAllFish();
        //生成随机数0~9999，共10000个
        int randomNum = RandomUtil.SecureRandomNum(0, 9999);
        for (BotGameFishing botGameFishing : allFish) {
            if (botGameFishing.getIsSpecial()) {
                //排除所有鱼中的特殊群组中的鱼
                if (!botGameFishing.getSpecialGroup().equals(groupMessage.getGroupUser().getGroupId())) {
                    continue;
                }
            }
            //根据随机数，取第一个随机数比概率值小的鱼
            if (10000 - randomNum < botGameFishing.getFishProbability() * 100) {
                System.out.println(randomNum);;
                return botGameFishing;
            }
        }
        return null;
    }

    //返回钓到的鱼，生成对应消息
    public static Message FishAFish(GroupMessage groupMessage, GroupCoin groupCoin) {
        MessageChainBuilder messageChainBuilder = new MessageChainBuilder();
        //直接获取数据库中对应人的鱼筐
        GroupUser groupUser = groupMessage.getGroupUser();
        GroupFishing groupFishing = groupFishingService.GetGroupFishingByGroupUser(groupUser);
        //获取一条鱼
        BotGameFishing botGameFishing = GetFish(groupMessage);
        //如果为空，则直接返回没钓到
        if (botGameFishing == null) {
            //图片路径
            File image = new File("./src/main/resources/image/no_fish.jpg");
            //上传图片
            Image groupImage = groupMessage.getGroupMessageEvent().getGroup().uploadImage(image);
            //扣除金币
            groupCoin.setCoin(groupCoin.getCoin() - 10);
            int status = groupCoinService.UpdateCoin(groupCoin);
            if (status == 0) {
                //插入失败提示
                messageChainBuilder.add("❌ 扣除金币失败，请联系开发者查看数据库连接问题");
            } else {
                //拼接回复消息
                messageChainBuilder.add("🤔 你啥都没钓到 扣除 10 金币");
                messageChainBuilder.add(groupImage);
                return messageChainBuilder.asMessageChain();
            }
        }
        //判断数据库中是否有这个人的鱼筐
        if (groupFishing == null) {
            //如果没有，则直接插入
            groupFishing = new GroupFishing(groupUser, botGameFishing.getFishId(), 1);
            int status = groupFishingService.InsertGroupFishing(groupFishing);
            //判断插入是否成功
            if (status == 0) {
                //插入失败提示
                messageChainBuilder.add("❌ 首次钓鱼失败，请联系开发者查看数据库连接问题");
            } else {
                //扣除金币
                groupCoin.setCoin(groupCoin.getCoin() - 10);
                int statusCost = groupCoinService.UpdateCoin(groupCoin);
                if (statusCost == 0) {
                    //插入失败提示
                    messageChainBuilder.add("❌ 扣除金币，请联系开发者查看数据库连接问题");
                } else {
                    //插入成功提示
                    messageChainBuilder.add("✔ 首次钓鱼成功 扣除 10 金币" + "\n" +
                            "🎣 你钓到了一条 \"" + botGameFishing.getFishName() + "\"\n" +
                            "🗑 鱼筐状态 1 / 5"
                    );
                }
            }
        } else {
            //如果数据库中有这个人的鱼筐
            //先获取空鱼筐的坑位
            int slotId = groupFishing.getNullSlot();
            //根据坑位id插入新的鱼
            groupFishing.setFishBySlotId(slotId, botGameFishing.getFishId());
            int status = groupFishingService.UpdateGroupFishing(groupFishing);
            if (status == 0) {
                //插入失败提示
                messageChainBuilder.add("❌ 钓鱼失败，请联系开发者查看数据库连接问题");
            } else {
                //扣除金币
                groupCoin.setCoin(groupCoin.getCoin() - 10);
                int statusCost = groupCoinService.UpdateCoin(groupCoin);
                if (statusCost == 0) {
                    //插入失败提示
                    messageChainBuilder.add("❌ 扣除金币，请联系开发者查看数据库连接问题");
                } else {
                    //插入成功提示
                    messageChainBuilder.add("✔ 钓鱼成功 扣除 10 金币" + "\n" +
                            "🎣 你钓到了一条 \"" + botGameFishing.getFishName() + "\"\n" +
                            "🗑 鱼筐状态 " + groupFishing.getSlotCount() + " / 5"
                    );
                }
            }
        }
        return messageChainBuilder.asMessageChain();
    }

    //群内鱼种查询
    public static Message FishTypeQuery(GroupMessage groupMessage) {
        MessageChainBuilder messageChainBuilder = new MessageChainBuilder();
        MessageChainBuilder normalFish = new MessageChainBuilder();
        MessageChainBuilder specialFish = new MessageChainBuilder();
        //消息模板
        normalFish.add("\uD83D\uDC1F 普通鱼种: " + "\n");
        specialFish.add("\uD83D\uDC20 限定鱼种: " + "\n");
        //群内是否有特殊鱼种类，默认为true，如果有的话就改为false
        boolean noSpecialFish = true;
        //获取所有鱼
        List<BotGameFishing> botGameFishingList = botGameFishingService.GetAllFish();
        //迭代所有鱼种类
        for (BotGameFishing botGameFishing : botGameFishingList) {
            //是否是特殊种类
            if (botGameFishing.getIsSpecial()) {
                //是否是对应群
                if (botGameFishing.getSpecialGroup().equals(groupMessage.getGroupUser().getGroupId())) {
                    specialFish.add(botGameFishing.getFishName() + "\n" +
                            "价值 " + botGameFishing.getFishValue() +
                            " 概率 " + botGameFishing.getFishProbability() + "\n"
                    );
                    noSpecialFish = false;
                }
            } else {
                normalFish.add(botGameFishing.getFishName() + "\n" +
                        "价值 " + botGameFishing.getFishValue() +
                        " 概率 " + botGameFishing.getFishProbability() + "\n"
                );
            }
        }
        if (noSpecialFish) {
            specialFish.add("无" + "\n" +"可通过 \"bot add fish 鱼名 鱼价值金币 概率\" 添加");
        }
        messageChainBuilder.add(normalFish.asMessageChain());
        messageChainBuilder.add(specialFish.asMessageChain());
        return messageChainBuilder.asMessageChain();
    }

    //鱼筐查询
    public static Message FishTubQuery(GroupMessage groupMessage) {
        MessageChainBuilder messageChainBuilder = new MessageChainBuilder();
        GroupFishing groupFishing = groupFishingService.GetGroupFishingByGroupUser(groupMessage.getGroupUser());
        messageChainBuilder.add("🗑️ 鱼筐状态" + "\n");
        //判断鱼筐是否为空
        if (groupFishing == null || groupFishing.getSlotCount() == 0) {
            //为空则直接返回
            messageChainBuilder.add("你的鱼筐空空如也，快去钓鱼试试吧");
        } else {
            int count = groupFishing.getSlotCount();
            //不为空则返回对应的鱼
            for (int i = 0; i < count && count <= 5; ++i) {
                String tmpFishId = groupFishing.getFishBySlot(i + 1);
                if (tmpFishId == null) {
                    ++count;
                } else {
                    messageChainBuilder.add(
                            "Slot " + (i + 1) + " " + botGameFishingService.GetFishNameById(tmpFishId) + "\n"
                    );
                }
            }
        }
        return messageChainBuilder.asMessageChain();
    }

    //卖鱼
    public static Message SellFish(GroupMessage groupMessage) {
        MessageChainBuilder messageChainBuilder = new MessageChainBuilder();
        messageChainBuilder.add("暂时未实现");
        return messageChainBuilder.asMessageChain();
    }
}