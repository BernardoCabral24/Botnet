import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private static List<Pair<String, String>> roleRank=new ArrayList<>();

    private Pair<String,String> currentRole;
    private int rankNum;

    public Role(){
        roleRank.add(new Pair<>("Owner","Infinity"));//0
        roleRank.add(new Pair<>("Admin","1800"));//1
        roleRank.add(new Pair<>("Vip","600"));//2
        roleRank.add(new Pair<>("Normal","100"));//3
        roleRank.add(new Pair<>("Free","20"));//4
    }
    public void setup(int n){
        try{
            currentRole=roleRank.get(n);
            rankNum=n;
        }catch(Exception ignored){

        }
    }

    public int getRankNum() {
        switch (rankNum){
            case 0:
            case 1:
                return -1;
            case 2:
                return 2;
            case 3:
            case 4:
                return 1;
            default:
                return 0;
        }
    }

    public Pair<String, String> getCurrentRole() {
        return currentRole;
    }
}
