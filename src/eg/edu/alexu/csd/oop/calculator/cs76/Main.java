package eg.edu.alexu.csd.oop.calculator.cs76;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;



public class Main extends Application implements Calculator  {
    private String res;
    private AtomicBoolean firstNegative=new AtomicBoolean(false);
    private AtomicBoolean secondNegative=new AtomicBoolean(false);
    private AtomicBoolean OperatorHere= new AtomicBoolean(false);
    private int curr=-1;
    private int pointer=0;
    private String[] answer = new String[5];
    private String[] eq=new String[5];

    @Override
    public void input(String s) {
        String[] arr;
        boolean n_n=false;
        if(OperatorHere.get()) {
            if (s.contains("+")) {
                arr = s.split("\\+", 2);
            } else if (s.contains("X"))
                arr = s.split("X", 2);
            else if (s.contains("/"))
                arr = s.split("/", 2);
            else{
                if(!firstNegative.get())
                arr = s.split("-", 2);
                else {
                    StringBuilder temp = new StringBuilder();
                    for(int i=1;i<s.length();i++)
                        temp.append(s.charAt(i));
                    s= temp.toString();
                    arr=s.split("-",2);
                    n_n=true;
                }

            }

            double a = Double.parseDouble(arr[0]);
            double b = Double.parseDouble(arr[1]);
            double re;
            if(n_n)
            {
                a*=-1;
            }
            if (s.contains("+")) {
                re = a + b;
                res = Double.toString(re);
            } else if (s.contains("X")) {
                re = a * b;
                res = Double.toString(re);
            } else if (s.contains("/")) {
                re = a / b;
                res = Double.toString(re);
            }
            else {
                    re=a-b;
                    res=Double.toString(re);
            }
        }
    }

    @Override
    public String getResult() {
        if(res.charAt(0)=='-'){
            boolean check=false;
            for (int i=1;i<res.length();i++)
            {
                if (res.charAt(i)!='0'&&res.charAt(i)!='.')
                {
                    check=true;
                    break;
                }
            }
            if(!check)res="0";
        }
        double a=Double.parseDouble(res);
        int b=(int)a;
        if(a/b==1)
            res=Integer.toString(b);
        return res;
    }

    @Override
    public String current() {
        pointer=curr;
        return Integer.toString(curr);
    }

    @Override
    public String prev() {
        return Integer.toString(pointer);
    }

    @Override
    public String next() {
        return Integer.toString(pointer);
    }

    @Override
    public void save() {
        try (PrintWriter out = new PrintWriter("data1.txt")) {
            for(int i=0;i<5;i++){
                out.println(eq[i]);
                out.println(answer[i]);
            }
            out.println(curr);
        }
        catch (FileNotFoundException ignored)
        {
        }

    }

    @Override
    public void load() throws IOException {
        File file = new File("data1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int counter=0,index=0;
        while (true){
            st = br.readLine();
            if(index==5){
                curr=Integer.parseInt(st);
                pointer=curr;
                break;
            }
            else if(counter%2==0) {
                if(st=="null")
                    eq[index]="a";
                else{
                    eq[index]=st;
                    counter++;
                }
            }
        else {
                if(st=="null")
                    answer[index]="a";
                else{
                    answer[index]=st;
                    index++;
                    counter++;
                }
            }
    }
}


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        Label equation=new Label();
        equation.setMinWidth(Region.USE_PREF_SIZE);
        equation.setAlignment(Pos.CENTER_LEFT);
        GridPane.setConstraints(equation,0,0,16,1);
        equation.setMaxWidth(400);
        equation.setFont(new Font("Arial", 15));
        equation.setTextFill(Color.web("#ffffff"));

        Label input=new Label("0");
        input.setMinWidth(Region.USE_PREF_SIZE);
        input.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(input,0,1,16,1);
        input.setMaxWidth(400);
        input.setFont(new Font("Arial", 50));
        input.setTextFill(Color.web("#ffffff"));

        Button clearALL = new Button("AC");
        clearALL.setFont(new Font("Arial", 20));
        GridPane.setConstraints(clearALL,0,4);
        clearALL.setOnAction(e->{input.setText("0");
        OperatorHere.set(false);
        firstNegative.set(false);
        secondNegative.set(false);
        equation.setText("");
        });
        Button del = new Button("Del");
        del.setFont(new Font("Arial", 20));
        GridPane.setConstraints(del,5,4);
        del.setOnAction(e->{
            if(!input.getText().equals(""))
            {
                if(OperatorHere.get())
                {
                    if(input.getText().charAt(input.getText().length()-1)=='+'||input.getText().charAt(input.getText().length()-1)=='X'||input.getText().charAt(input.getText().length()-1)=='/')
                        OperatorHere.set(false);
                    else if(input.getText().charAt(input.getText().length()-1)=='-'&&secondNegative.get()&&(input.getText().charAt(input.getText().length()-2)=='+'||input.getText().charAt(input.getText().length()-2)=='X'||input.getText().charAt(input.getText().length()-2)=='/'||input.getText().charAt(input.getText().length()-2)=='-'))
                        secondNegative.set(false);
                    else if(input.getText().charAt(input.getText().length()-1)=='-'&&!secondNegative.get())
                        OperatorHere.set(false);
                }
                StringBuilder temp= new StringBuilder();
                for(int i=0;i<input.getText().length()-1;i++)
                {
                    temp.append(input.getText().charAt(i));
                }
                if(temp.toString().equals(""))
                    temp.append("0");
                input.setText(temp.toString());
            }
        });

        Button point = new Button(".");
        point.setFont(new Font("Arial", 30));
        GridPane.setConstraints(point,10,16);
        point.setOnAction(e->{
            if(input.getText().length()<12) {
                if (input.getText().charAt(input.getText().length() - 1) == '+' || input.getText().charAt(input.getText().length() - 1) == '-' || input.getText().charAt(input.getText().length() - 1) == 'X' || input.getText().charAt(input.getText().length() - 1) == '/') {
                    input.setText(input.getText() + "0.");
                } else if (decimal_checker(input.getText()))
                    input.setText(input.getText() + ".");
                else AlertBox.display("Mate", "How many decimal points do you expect in a number!?");
            }
            else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });

        Button prev=new Button("Prev");

        Button next=new Button("Next");
        next.setFont(new Font("Arial", 20));
        GridPane.setConstraints(next,20,7);
        next.setDisable(true);
        next.setOnAction(e-> {
            String t=next();
        pointer=(pointer+1+5)%5;
        input.setText(answer[pointer]);
        equation.setText(eq[pointer]);
        if(answer[(pointer+1+5)%5]==null)
            next.setDisable(true);
        prev.setDisable(false);
        });

        prev.setFont(new Font("Arial", 20));
        GridPane.setConstraints(prev,20,4);
        prev.setDisable(true);
        prev.setOnAction(e-> {
            String t=prev();
            pointer=(pointer-1+5)%5;
            next.setDisable(false);
            input.setText(answer[pointer]);
            equation.setText(eq[pointer]);
            if(answer[(pointer-1+5)%5]==null||answer[(pointer-1+5)%5]=="a")
                prev.setDisable(true);
        });

        AtomicBoolean prevEnabler= new AtomicBoolean(false);
        Button curren=new Button("Current");


        Button save=new Button("Save");
        save.setDisable(true);
        save.setFont(new Font("Arial", 20));
        GridPane.setConstraints(save,20,10);
        save.setOnAction(e->{
            save();
        });
        Button load=new Button("Load");
        load.setFont(new Font("Arial", 20));
        GridPane.setConstraints(load,20,13);
        load.setOnAction(e->{
            try {
                load();
                curren.setDisable(false);
                prev.setDisable(false);
                next.setDisable(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        curren.setFont(new Font("Arial", 15));
        GridPane.setConstraints(curren,10,4);
        curren.setDisable(true);
        curren.setOnAction(e->{
            String t=current();
            int index=Integer.parseInt(t);
            if(prevEnabler.get())
            prev.setDisable(false);
            input.setText(answer[index]);
            equation.setText(eq[index]);
            next.setDisable(true);
        });

        Button equal = new Button();
        GridPane.setConstraints(equal,15,16);
        equal.setText("=");
        equal.setFont(new Font("Arial", 30));
        equal.setOnAction(e -> {
            if(input.getText().charAt(input.getText().length()-1)=='+'||input.getText().charAt(input.getText().length()-1)=='-'||input.getText().charAt(input.getText().length()-1)=='X'||input.getText().charAt(input.getText().length()-1)=='/')
                AlertBox.display("Hey","Where is the second operand!?");
            else if(!OperatorHere.get()) AlertBox.display("duh!", "-_-");
            else if(input.getText().contains("/"))
            {
                int index=0;
                for(int i=0;i<input.getText().length();i++)
                {
                    if(input.getText().charAt(i)=='/')
                    {
                        index=i;
                        break;
                    }
                }
                boolean checker=false;
                for(int i=index+1;i<input.getText().length();i++)
                {
                    if(input.getText().charAt(i)!='0'&&input.getText().charAt(i)!='.')
                    {
                        checker=true;
                        break;
                    }
                }
                if(checker)
                {
                    input(input.getText());
                    equation.setText(input.getText());
                    input.setText(getResult());
                    OperatorHere.set(false);
                    firstNegative.set(false);
                    secondNegative.set(false);
                    if(input.getText().charAt(0)=='-')
                        firstNegative.set(true);
                    curr++;
                    curr=curr%5;
                    pointer=curr;
                    answer[curr]=input.getText();
                    eq[curr]=equation.getText();
                    curren.setDisable(false);
                    if(curr>0)
                        prevEnabler.set(true);
                    if(prevEnabler.get())
                        prev.setDisable(false);
                    save.setDisable(false);
                 //   System.out.println(curr);
                }
                else AlertBox.display("MATH ERROR","ARE YOU KIDDING ME!!!!");
            }
            else {
                    input(input.getText());
                    equation.setText(input.getText());
                    input.setText(getResult());
                    OperatorHere.set(false);
                    firstNegative.set(false);
                    secondNegative.set(false);
                    if(input.getText().charAt(0)=='-')
                        firstNegative.set(true);
                curr++;
                curr=curr%5;
                pointer=curr;
                answer[curr]=input.getText();
                eq[curr]=equation.getText();
                    curren.setDisable(false);
                if(curr>0)
                    prevEnabler.set(true);
                if(prevEnabler.get())
                    prev.setDisable(false);
                save.setDisable(false);
            }
        });

        Button add = new Button();
        add.setFont(new Font("Arial", 30));
        GridPane.setConstraints(add,15,13);
        add.setText("+");
        add.setOnAction(e -> {
            if(input.getText().length()<12) {
                if (!(OperatorHere).get()) {
                    OperatorHere.set(true);
                    if (input.getText().charAt(input.getText().length() - 1) == '.')
                        input.setText(input.getText() + "0+");
                    else input.setText(input.getText() + "+");
                } else AlertBox.display("Dude", "One Operation at a time");
            }
            else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button subtrac = new Button();
        subtrac.setFont(new Font("Arial", 30));
        GridPane.setConstraints(subtrac,15,10);
        subtrac.setText("-");
        subtrac.setOnAction(e -> {
            if(input.getText().length()<12) {
                if (!(OperatorHere).get()) {
                    OperatorHere.set(true);
                    if (input.getText().charAt(input.getText().length() - 1) == '.')
                        input.setText(input.getText() + "0-");
                    else input.setText(input.getText() + "-");
                } else AlertBox.display("Dude", "One Operation at a time");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button multiply = new Button();
        GridPane.setConstraints(multiply,15,7);
        multiply.setText("X");
        multiply.setFont(new Font("Arial", 30));
        multiply.setOnAction(e -> {
            if(input.getText().length()<12) {
                if (!(OperatorHere).get()) {
                    OperatorHere.set(true);
                    if (input.getText().charAt(input.getText().length() - 1) == '.')
                        input.setText(input.getText() + "0X");
                    else input.setText(input.getText() + "X");
                } else AlertBox.display("Dude", "One Operation at a time");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button divide = new Button();
        GridPane.setConstraints(divide,15,4);
        divide.setText("/");
        divide.setOnAction(e -> {
            if(input.getText().length()<12) {
                if (!(OperatorHere).get()) {
                    OperatorHere.set(true);
                    if (input.getText().charAt(input.getText().length() - 1) == '.')
                        input.setText(input.getText() + "0/");
                    else input.setText(input.getText() + "/");
                } else AlertBox.display("Dude", "One Operation at a time");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        divide.setFont(new Font("Arial", 30));
        Button zero = new Button();
        zero.setFont(new Font("Arial", 30));
        GridPane.setConstraints(zero,5,16);
        zero.setText("0");
        zero.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "0");
                }
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button one = new Button();
        GridPane.setConstraints(one,0,13);
        one.setText("1");
        one.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "1");
                } else input.setText("1");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        one.setFont(new Font("Arial", 30));
        Button two = new Button();
        GridPane.setConstraints(two,5,13);
        two.setText("2");
        two.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "2");
                } else input.setText("2");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        two.setFont(new Font("Arial", 30));
        Button three = new Button();
        GridPane.setConstraints(three,10,13);
        three.setText("3");
        three.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "3");
                } else input.setText("3");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        three.setFont(new Font("Arial", 30));
        Button four = new Button();
        four.setText("4");
        GridPane.setConstraints(four,0,10);
        four.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "4");
                } else input.setText("4");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        four.setFont(new Font("Arial", 30));
        Button five = new Button();
        GridPane.setConstraints(five,5,10);
        five.setText("5");
        five.setFont(new Font("Arial", 30));
        five.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "5");
                } else input.setText("5");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button six = new Button();
        six.setFont(new Font("Arial", 30));
        GridPane.setConstraints(six,10,10);
        six.setText("6");
        six.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "6");
                } else input.setText("6");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button seven = new Button();
        GridPane.setConstraints(seven,0,7);
        seven.setText("7");
        seven.setFont(new Font("Arial", 30));
        seven.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "7");
                } else input.setText("7");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button eight = new Button();
        GridPane.setConstraints(eight,5,7);
        eight.setText("8");
        eight.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "8");
                } else input.setText("8");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        eight.setFont(new Font("Arial", 30));
        Button nine = new Button();
        GridPane.setConstraints(nine,10,7);
        nine.setFont(new Font("Arial", 30));
        nine.setText("9");
        nine.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!input.getText().equals("0")) {
                    input.setText(input.getText() + "9");
                } else input.setText("9");
            }else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });
        Button negative=new Button("+/-");
        negative.setFont(new Font("Arial", 23));
        GridPane.setConstraints(negative,0,16);
        negative.setOnAction(e->{
            if(input.getText().length()<12) {
                if (!OperatorHere.get()) {
                    StringBuilder temp = new StringBuilder();
                    if (!firstNegative.get()) {
                        temp.append("-");
                        firstNegative.set(true);
                        for (int i = 0; i < input.getText().length(); i++) {
                            temp.append(input.getText().charAt(i));
                        }
                    } else {
                        firstNegative.set(false);
                        for (int i = 1; i < input.getText().length(); i++) {
                            temp.append(input.getText().charAt(i));
                        }
                    }
                    input.setText(temp.toString());
                } else {
                    int index = 0;
                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < input.getText().length(); i++) {
                        if (i != 0 && (input.getText().charAt(i) == '+' || input.getText().charAt(i) == '-' || input.getText().charAt(i) == 'X' || input.getText().charAt(i) == '/')) {
                            temp.append(input.getText().charAt(i));
                            index = i;
                            break;
                        }
                        temp.append(input.getText().charAt(i));
                    }
                    if (!secondNegative.get()) {
                        secondNegative.set(true);
                        temp.append("-");
                    } else {
                        secondNegative.set(false);
                        index++;
                    }
                    for (int i = index + 1; i < input.getText().length(); i++) {
                        temp.append(input.getText().charAt(i));
                    }
                    input.setText(temp.toString());
                }
            }
            else {
                AlertBox.display("MaxSize","You have reached your Max number of characters");
            }
        });

        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setHgap(7);
        layout.setVgap(12);

        layout.getChildren().addAll(input,equation, negative,equal,curren,load,prev,next,save, add, divide, subtrac, multiply, clearALL, del, point, zero, one, two, three, four, five, six, seven, eight, nine);
        Scene scene=new Scene(layout,Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        layout.setStyle("-fx-background-color: #111213");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean decimal_checker(String text) {
        boolean c=false;
        boolean a=false;
        if(text.contains("+")||text.contains("-")||text.contains("X")||text.contains("/"))
        {
            for(int i=0;i<text.length();i++)
            {
                if((text.charAt(i)=='+'||text.charAt(i)=='X'||text.charAt(i)=='/')||(text.charAt(i)=='-'&&i!=0))
                {
                    a=true;
                }
                if(a)
                {
                    if(text.charAt(i)=='.')
                        return false;
                }
            }
        }
        else
        {
            for(int i=0;i<text.length();i++)
            {
                if(text.charAt(i)=='.')
                {
                    c=true;
                }
                if(c)
                    return false;
            }
        }
        return true;
    }


}
