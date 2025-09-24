package src;

public class Palestrante {
    private String id; //Referente a numeração do palestrante no site de eventos
    private String name;
    private String email;
    private String work;
    private String diretorioImage;

    public Palestrante(String id, String name, String work, String email, String image){
        this.name = name;
        this.email = email;
        this.work = work;
        this.diretorioImage = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork() {
        return this.work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDiretorioImage() {
        return this.diretorioImage;
    }

    public void setDiretorioImage(String diretorioImage) {
        this.diretorioImage = diretorioImage;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
