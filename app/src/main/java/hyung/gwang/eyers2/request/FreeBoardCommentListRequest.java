package hyung.gwang.eyers2.request;

public class FreeBoardCommentListRequest {
    String content;
    String name;
    String fbseq;
    int seq;
    public FreeBoardCommentListRequest(String content, String name, String fbseq, int seq){
        this.content = content;
        this.name = name;
        this.fbseq = fbseq;
        this.seq = seq;
    }
    public String getContent(){
        return content;
    }
    public void settContent(String content){
        this.content = content;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getFbseq(){
        return fbseq;
    }
    public void setDate(String fbseq){
        this.fbseq = fbseq;
    }
    public int getSeq(){
        return seq;
    }
    public void setSeq(int seq){
        this.seq = seq;
    }
}
