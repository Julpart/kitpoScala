package kitpo;

import java.util.Comparator;

public interface IUserType {
    public IUserType copy();

    public IUserType create();

    public String getClassName();

    public Comparator<IUserType> getTypeComparator();

}
