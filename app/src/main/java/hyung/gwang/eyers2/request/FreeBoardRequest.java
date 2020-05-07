package hyung.gwang.eyers2.request;

public class FreeBoardRequest {
    String content;
    String title;
    String name;
    String date;
    int seq;
    public FreeBoardRequest(String content, String name,String date, int seq, String title){
        this.content = content;
        this.title = title;
        this.name = name;
        this.date = date;
        this.seq = seq;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String notice){
        this.content = notice;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public int getSeq(){
        return seq;
    }
    public void setSeq(int seq){
        this.seq = seq;
    }
}
