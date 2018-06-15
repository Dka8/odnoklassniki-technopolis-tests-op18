package model;

import java.util.Random;

public class GroupInfo {
    private final String name;
    private final String description;
    private final String subcategory;
    public final String pathToCover = "/home/wave/Downloads/Mass_effect_n7.png";

    public static GroupInfo create(){
        Random rnd = new Random();
        return new GroupInfo(
                Integer.toString(rnd.nextInt()),
                Integer.toString(rnd.nextInt()),
                getSubcategory(rnd.nextInt(19)));
    }

    private GroupInfo(String name, String description, String subcategory) {
        this.name = name;
        this.description = description;
        this.subcategory = subcategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSubcategory() {
        return subcategory;
    }

    private static String getSubcategory(int index){
        return new String[] {
                "",
                "Авто и мото",
                "Блоги",
                "Дети",
                "Дизайн",
                "Животные",
                "Игры",
                "Кино и ТВ",
                "Книги и периодика",
                "Компьютер и интернет",
                "Красота и здоровье",
                "Кулинария",
                "Культура и искусство",
                "Музыка",
                "Путешествия и туризм",
                "Спорт и активный отдых",
                "Творчество",
                "Фан клуб",
                "Юмор"}[index];
    }
}
