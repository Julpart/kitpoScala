package List


interface IUserType {
    fun copy(): IUserType?

    fun create(): IUserType?

    fun getClassName(): String?

    fun getTypeComparator(): Comparator<IUserType>
}