package osiristherNative.entities;

/**
 * Created by DesiresDesigner on 19.02.15.
 */
public class IntFLG {
    private int flg;

    public IntFLG(){
        flg = 0;
    }

    IntFLG(int flg){
        this.flg = flg;
    }

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public void incFLG(){
        ++flg;
    }
}
