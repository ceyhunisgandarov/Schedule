package az.orient.homework.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumStatus {

    DEACTIVE(0), ACTIVE(1), PENDING(2), SENDINGMAIL(3), CONFIRM(4);

    public int value;

}
