package App.model;

public enum MenuItems {
    EXIT("0. End program"),
    ADD("1. Add new user"),
    SEARCH_BY_NAME("2. Search by Name"),
    SEARCH_BY_EMAIL("3. Search by Email");

    private String name;

    MenuItems(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    }

