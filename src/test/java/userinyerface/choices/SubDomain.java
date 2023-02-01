package userinyerface.choices;

public enum SubDomain {
    DOT_ORG(".org"),
    DOT_CO_DOT_UK(".co.uk"),
    DOT_NET(".net"),
    DOT_GOV(".gov"),
    DOT_DE(".de"),
    DOT_FR(".fr"),
    DOT_NL(".nl"),
    DOT_COM(".com"),
    DOT_BE(".be"),
    DOT_JPG(".jpg");

    public final String text;
    SubDomain(String text){
        this.text = text;
    }
}
