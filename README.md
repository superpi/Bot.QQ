# 扶摇 bot
一个基于 [Mirai](https://github.com/mamoe/mirai) 、Spring Boot、MySQL、和 Mybatis 的 QQ 机器人。也算是用学校学的点东西做的练手小玩具。

# 目录

- [已实现功能](#已实现功能)
- [操作列表](#操作列表)
  * [群内相关事件提醒](#群内相关事件提醒)
  * [签到和金币](#签到和金币)
  * [游戏](#游戏)
  * [查询](#查询)
- [更新日志](#更新日志)
  * [最新](#更新日志)
  * [3.0.0 +](#2020-11-10)
  * [2.0.0 +](#2020-07-12)
  * [1.0.0 +](#2020-07-07)
  * [0.0.1 +](#2020-06-24)
  * [初版](#2020-06-22)

# 已实现功能
00. 可能有小彩蛋
01. 群内相关事件提醒
    - 群员进群 / 退群 / 踢出 / 被禁言 / 解除禁言 / 设为管理员 / 取消都会提醒，并将在 1 分钟后自动撤回
    - 群荣誉事件提醒
    - 群成员获得新头衔后的提醒
02. 签到
    - 每天间隔 8 小时可签到一次
    - 签到后可查询经验值
    - 不同经验值有不同的头衔，每个群可以单独定义
03. 金币
    - 每天间隔 8 小时可领取一次金币
    - 使用小游戏功能时会消耗 / 获得金币
    - 金币目前可以在群内转移
04. 游戏
    01. 钓鱼小游戏
        - 当经验值和金币达到一定数量时可以玩这个小游戏
        - 鱼的种类有两种：所有群都有的普通鱼和单独群自定义特殊鱼
        - 单独群自定义特殊鱼的名字、概率、价值均可由群管理员自定义
        - 每名群员有一个容量为 5 的鱼筐，鱼筐满后继续钓鱼会从第一个位置开始覆盖
        - 鱼筐满后可以指定卖出一条鱼，卖出后获得的金币数量为卖出鱼的价值的 50%
    02. 炉石传说抽卡
        - 发送指令后，机器人会从数据库中随机抽取五张卡，然后返回
    03. 投骰子和石头剪刀布
05. 查询
    - 守望先锋当日街机模式查询
    - 当前时间，包含农历
06. 其他功能正在重写

# 操作列表
*<p align="right">最后更新时间：2021-03-06</p>*
*<p align="right">对应版本：4.1.5.48 +</p>*

*使用功能前请在最前方加入~或/符号，例如“~签到”或“/签到”。*

### 群内相关事件提醒

|  序号  |  提醒类型  |  是否自动撤回  |
|  :----:  |  :----:  |  :----:  |
|  1  |  群员进群 / 退群 / 踢出 / 被禁言 / 解除禁言 / 设为管理员 / 取消都会提醒  |  1 分钟后自动撤回  |
|  2  |  群荣誉事件提醒  |  否  |
|  3  |  群成员获得新头衔后的提醒  |  否  |
|  4  |  群内成员发闪照  |  否  |
|  5  |  群内成员发红包  |  否  |

### 签到和金币

|  序号   | 关键字  |  功能  |
|  :----:  | :----:  |  :----: |
|  1  | 签到 |  发送签到，获取经验值  |
|  2  | EXP查询 / 经验查询  |  签到经验值查询,发送EXP查询，获取本人在该群的经验值  |
|  3  |  领金币  |  发送领金币，获取金币  |
|  4  |  金币查询  |  金币查询,发送金币查询，获取本群的经验值  |
|  5  |  金币转移  |  发送“金币转移 数量 @接收人”，可以将金币转移给他人  |

### 游戏

##### 钓鱼

|  序号  |  关键字  |  功能  |
|  :----:  | :----:  |  :----: |
|  1  |  钓鱼  |  发送“钓鱼”，就可以钓一条鱼  |
|  2  |  鱼种查询  |  发送“鱼种查询”  |
|  3  |  鱼筐查询 / 鱼框状态  |  查看鱼筐状态  |
|  4  |  福利金币  |  领取福利金币  |
|  5  |  卖鱼  |  卖鱼  |

##### 抽卡

|  序号  | 关键字  |  功能  |
|  :----:  |  :----:  |  :----:  |
|  1  |  炉石传说或hs  |  炉石传说最新扩展包卡牌的开包  |

##### 其它游戏

|  序号  | 关键字  |  功能  |
|  :----:  |  :----:  |  :----:  |
|  1  |  投骰子或roll dice  |  投骰子  |
|  2  |  石头剪刀布或rps  |  石头剪刀布  |

### 查询
|  序号  |  关键字  |  功能  |
|  :----:  |  :----:  |  :----:  |
|  1  |  守望街机模式或ow mode  |  守望先锋当日街机模式查询  |
|  2  |  时间或time  |  当前时间，包含农历  |

*<p align="right">想要啥功能可以私聊发给机器人，看到后会做。</p>*

# 更新日志

## 2021-03-06
#### 4.1.5.48
* 优化文案
* 添加时间查询、守望先锋当日街机模式查询、炉石抽卡游戏、投骰子和石头剪刀布。
* 更新README

## 2021-03-05
#### 4.1.4.47
* 优化log文案
* 优化dev环境判断
* 完善监听的Group事件
* 更新Mirai为2.4.2

## 2021-03-03
#### 4.1.3.46
* 添加发送消息后的log功能
* 添加启动后成功提醒
* 添加复读消息的功能
* 添加红包检测
* 优化提醒文案
* 优化缓存文件的路径
* 优化代码

## 2021-03-01
#### 4.1.2.45

* 修复Linux下的文件路径问题

#### 4.1.1.44

* 更新为 Mirai 2.4.1
* 优化入群和群荣誉更改时的文案
* 添加闪照提醒

## 2021-02-25
#### 4.1.0.43

* 更新为 Mirai 2.3.0
* 优化入群和群荣誉更改时的文案
* 更新依赖版本

## 2021-01-19

#### 4.0.0.42

* 更新为 Mirai 2.0.0
* 更新依赖版本
* 完全重写，改为使用Mybatis
* 更新 README

## 2020-11-10

#### 3.4.4.41

* 更新为 Mirai 1.3.3
* 更新Spring Boot、hutool等依赖版本
* 去除部分消息提醒
* 暂时关闭点歌功能

## 2020-10-05

#### 3.4.3.40

* 更新为 Mirai 1.3.2
* 去除闪照@提醒

## 2020-09-25

#### 3.4.2.39

* 支持同时@多人戳
* 将功能列表展示方式修改为链接

## 2020-09-19

#### 3.4.1.38

* 优化戳一戳

## 2020-09-18

#### 3.4.0.37

* 更新为 Mirai 1.3.0
* 添加戳一戳

## 2020-09-06

#### 3.3.3.36

* 更新早午晚问候文案和时间划分，来自[维基百科](https://zh.wikipedia.org/zh-cn/Template:一天里的时间细分)
* 修复进群欢迎不生效的问题
* 添加新的工具类
* 修改工具类中的函数名
## 2020-08-26
#### 3.3.2.35
* 修复部分文案问题
* 修复算错了的毫秒（1 分钟 60000 毫秒）
* 修改 bot 功能图片获取的方式
* 更新为 Mirai 1.2.2
## 2020-08-22
#### 3.3.1.34 (Hotfix)
* 修复部分功能在私聊中不可用的问题
* 修改部分文案
    * 优化入群后第一条消息
* 精简代码
#### 3.3.0.33
* 所有功能可在私聊中使用
* 所有群事件提醒 (如退群、被禁言) 将在1分钟内自动撤回
* 修改部分文案
    * 私聊时的可用功能提醒
    * 优化群事件提醒中的群员名片和昵称选择
    * 新成员提醒增加头像
    * 更新日志文案优化
    * 时间文案优化
* 精简代码
## 2020-08-20
#### 3.2.0.32
* 添加自动同意加好友、进群
* 添加处理事件处理时抛出的异常
* 修改协议为Android Pad
#### 3.1.9.31 (Hotfix)
* 修复闪照、红包检测
* 修改部分文案
* 修改协议为Android Watch
#### 3.1.8.30
* 添加群员被kick提醒
* 修改部分文案
* 更新为Mirai 1.2.1
* 更新依赖
## 2020-08-10
#### 3.1.7.29
* 修改部分文案
* 添加API等待提示
## 2020-08-09
#### 3.1.6.28
* 机器人功能列表改为发送图片
* 优化部分代码逻辑
* 修改部分文案
* 去除触发日志
## 2020-07-27
#### 3.1.5.27
* 修复机器人被@
* 修改部分文案
## 2020-07-25
#### 3.1.4.26 (Hotfix)
* 修复闪照检测
* 修改签到文案，避免刷屏
#### 3.1.3.25
* 更新为Mirai 1.1.3
* 签到功能测试结束，已全功能上线，测试数据已全部删除
* 优化签到功能，修复签到查询和排名
* 实现部分指令功能
## 2020-07-20
#### 3.1.2.24
* 优化签到功能，实现签到查询和排名
## 2020-07-17
#### 3.1.1.23
* 优化签到功能，实现多群签到
## 2020-07-16
#### 3.1.0.22
* 发送 "sudo get/set" 命令查询和修改参数
* 修复部分问题
* 优化文案
## 2020-07-15
#### 3.0.0.21
* 从数据库读取参数和配置
* 修复部分问题
## 2020-07-12
#### 2.3.1.19
* 修复部分问题。
* 添加功能：
    * 优化功能17。
* 删除功能
    * 删除功能16、17（部分）
## 2020-07-11
#### 2.3.0.18
* 修复部分问题。
* 添加功能：
    * 实现功能17。
#### 2.2.0.17
* 修复部分问题。
* 添加功能：
    * 实现功能15、16。
## 2020-07-10
#### 2.1.0.16
* 修复部分问题。
* 修改yaml文件匹配关键字的规则。
## 2020-07-09
#### 2.0.0.15
* 重构代码，使用Mirai(https://github.com/mamoe/mirai)
* 添加功能：
    * 实现功能14。
## 2020-07-07
#### 1.1.4.14
* 添加讨论组消息监听和发送。
* 添加功能：
    * 实现功能13。
## 2020-06-29
#### 1.1.3.13
* 修复问题，优化代码。
* 随机复读的阈值改为从配置文件中读取。
## 2020-06-28
#### 1.1.2.12
* 添加网易云点歌。
* 配置文件改为从外部读取。
## 2020-06-27
#### 1.1.1.11
* 添加功能：
    * 实现功能12。
* 优化早午晚问候。
#### 1.1.0.10
* 修复复读和闪照。
* 除特别注明外，所有功能群聊和私聊均可用。
## 2020-06-26
#### 1.0.0.9
* 重构代码，使用原生Java，减少内存占用。
## 2020-06-24
#### 0.4.0.8
* 使用反射，重构代码。
* 添加用户和群黑名单功能，在黑名单中的群或用户不会触发概率复读。
* 不会随机复读触发功能的关键字。
#### 0.3.0.7
* 重构代码。
#### 0.2.3.6
* 减少智械危机的发生。
#### 0.2.2.5
* 添加功能：
    * 实现功能10~11。
## 2020-06-23
#### 0.2.1.4
* 添加功能：
    * 实现功能09。
* 重构代码。
#### 0.2.0.3
* 添加功能：
    * 实现功能08。
#### 0.1.0.2
* 添加功能：
    * 实现功能05~07。
* 重构代码。
## 2020-06-22
#### 0.0.1.1
* 首次更新。
    * 实现功能01~04。
