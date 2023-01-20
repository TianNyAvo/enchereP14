package s5.cloud.enchere.model;

import s5.cloud.enchere.exception.CustomException;

public abstract class HasFK<FK> extends HasId {
    public abstract void setFK(FK fk) throws CustomException;
}
