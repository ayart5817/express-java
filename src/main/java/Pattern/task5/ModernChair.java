package Pattern.task5;

class ModernChair implements Chair{
    @Override
    public void sitOn() {
        System.out.println("Сидим на модерн стуле кожа + метал");
    }
}
class ClassicChar implements Chair {

    @Override
    public void sitOn() {
        System.out.println("Сидим на классическом стуле");
    }
}

class ModernTable implements Table {

    @Override
    public void use() {
        System.out.println("Используем современный стол стеклянная поверхность ");
    }
}
class ClassicTable implements Table {
    @Override
    public void use() {
        System.out.println("Используем классический стол из дерева");
    }
}
