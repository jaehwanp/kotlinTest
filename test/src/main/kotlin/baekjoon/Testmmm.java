package baekjoon;

public class Testmmm {
    public static void main(String[] args) {
        int answer = 0;

        String s = "";
        String[] arr = s.split("");

        Ob ob = new Ob();
        ob.setAnswer(answer);

        for (String i : arr) {
            if(i.equals("a") || i.equals("z")) {
                if(ob.getCurrent() == null) {
                    ob.setCurrent(i);
                }
                ob.change(i);
            }
        }
        answer = ob.getAnswer();
        System.out.println(answer);
    }
}

class Ob {
    private String current;
    private int answer;
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public int getAnswer() {
        return answer;
    }
    public void setCurrent(String current) {
        this.current = current;
    }
    public String getCurrent() {
        return current;
    }
    public void change(String qq) {
        if(qq.equals("a") && getCurrent().equals("z")) {
            setCurrent("a");
            setAnswer(getAnswer()+1);
        } else if(qq.equals("z") && getCurrent().equals("a")) {
            setCurrent("z");
            setAnswer(getAnswer()+1);
        }
    }
}
