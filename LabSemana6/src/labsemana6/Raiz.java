package labsemana6;

import java.io.File;

public class Raiz {
    public static File root(){
        File raiz = new File(System.getProperty("user.dir"), "Sistema");
        if(!raiz.exists())
            raiz.mkdirs();

        return raiz;
    }
}
