package labsemana6;

public class Raiz {
    public static String root(){
        String cd = System.getProperty("user.dir");
        if(cd.endsWith("LabSemana6")){
            return "Sistema";
        }
        else{
            return "LabSemana6/Sistema";
        }
    }
}
