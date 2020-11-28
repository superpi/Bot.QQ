package ninja.skyrocketing.bot.fuyao.pojo.group;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupExpRankName {
    private Long groupId;

    private Integer expOffset;

    private String rank1;

    private String rank2;

    private String rank3;

    private String rank4;

    private String rank5;

    private String rank6;

    private String rank7;
}