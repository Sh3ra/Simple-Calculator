package eg.edu.alexu.csd.oop.calculator.cs76;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.math.BigInteger;
import java.util.Dictionary;

import static javafx.application.Application.launch;


public class Main extends Application implements Calculator  {
    private String res;
    @Override
    public void input(String s) {
        String[] arr;
        if(s.contains("+"))
        {
            arr = s.split("\\+", 2);
        }
        else if(s.contains("-"))
            arr=s.split("-",2);
        else if(s.contains("X"))
            arr=s.split("X",2);
        else if (s.contains("/"))
        arr=s.split("/",2);
        else {
            res=s;
            return;
        }
        double a=Double.parseDouble(arr[0]) ;
        double b=Double.parseDouble(arr[1]) ;
        double re;
        if(s.contains("+"))
        {
            re=a+b;
            res=Double.toString(re);
        }
        else if(s.contains("-"))
        {
            re=a-b;
            res=Double.toString(re);
        }
        else if(s.contains("X"))
        {
            re=a*b;
            res=Double.toString(re);
        }else if(s.contains("/"))
        {
            re=a/b;
            res=Double.toString(re);
        }
    }

    @Override
    public String getResult() {
        return res;
    }

    @Override
    public String current() {
        return null;
    }

    @Override
    public String prev() {
        return null;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        Label input=new Label("0");
        input.setMinWidth(Region.USE_PREF_SIZE);
        input.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(input,0,1,16,1);
        input.setMaxWidth(450);
        input.setFont(new Font("Arial", 50));
        input.setTextFill(Color.web("#ffffff"));

        Button clearALL = new Button("AC");
        clearALL.setFont(new Font("Arial", 20));
        GridPane.setConstraints(clearALL,0,4);
        clearALL.setOnAction(e->input.setText("0"));
        Button del = new Button("Del");
        del.setFont(new Font("Arial", 20));
        GridPane.setConstraints(del,5,4);
        del.setOnAction(e->{
            if(!input.getText().equals(""))
            {
                String temp="";
                for(int i=0;i<input.getText().length()-1;i++)
                {
                    temp=temp+input.getText().charAt(i);
                }
                if(temp.equals(""))
                    temp+="0";
                input.setText(temp);
            }
        });
        Button point = new Button(".");
        point.setFont(new Font("Arial", 30));
        GridPane.setConstraints(point,10,16);
        point.setOnAction(e->{
            if(input.getText().charAt(input.getText().length()-1)=='+'||input.getText().charAt(input.getText().length()-1)=='-'||input.getText().charAt(input.getText().length()-1)=='X'||input.getText().charAt(input.getText().length()-1)=='/')
            {
                input.setText(input.getText()+"0.");
            }
            else if(decimal_checker(input.getText()))
                input.setText(input.getText()+".");
            else AlertBox.display("Mate","How many decimal points do you expect in a number!?");
        });
        Button equal = new Button();
        GridPane.setConstraints(equal,15,16);
        equal.setText("=");
        equal.setFont(new Font("Arial", 30));
        equal.setOnAction(e -> {
            if(input.getText().charAt(input.getText().length()-1)=='+'||input.getText().charAt(input.getText().length()-1)=='-'||input.getText().charAt(input.getText().length()-1)=='X'||input.getText().charAt(input.getText().length()-1)=='/')
                AlertBox.display("Hey","Where is the second operand!?");
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
                    input.setText(getResult());
                }
                else AlertBox.display("MATH ERROR","ARE YOU KIDDING ME!!!!");
            }
            else {
                input(input.getText());
                input.setText(getResult());
            }
        });
        Button add = new Button();
        add.setFont(new Font("Arial", 30));
        GridPane.setConstraints(add,15,13);
        add.setText("+");
        add.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0+");
                else input.setText(input.getText()+"+");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Button subtrac = new Button();
        subtrac.setFont(new Font("Arial", 30));
        GridPane.setConstraints(subtrac,15,10);
        subtrac.setText("-");
        subtrac.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0-");
                else input.setText(input.getText()+"-");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Button multiply = new Button();
        GridPane.setConstraints(multiply,15,7);
        multiply.setText("X");
        multiply.setFont(new Font("Arial", 30));
        multiply.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0X");
                else input.setText(input.getText()+"X");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Button divide = new Button();
        GridPane.setConstraints(divide,15,4);
        divide.setText("/");
        divide.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0/");
                else input.setText(input.getText()+"/");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        divide.setFont(new Font("Arial", 30));
        Button zero = new Button();
        zero.setFont(new Font("Arial", 30));
        GridPane.setConstraints(zero,5,16);
        zero.setText("0");
        zero.setOnAction(e->{
        if(!input.getText().equals("0"))
        {
            input.setText(input.getText()+"0");
        }
        });
        Button one = new Button();
        GridPane.setConstraints(one,0,13);
        one.setText("1");
        one.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"1");
        }
        else input.setText("1");
        });
        one.setFont(new Font("Arial", 30));

        Button two = new Button();
        GridPane.setConstraints(two,5,13);
        two.setText("2");
        two.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"2");
        }
        else input.setText("2");
        });
        two.setFont(new Font("Arial", 30));

        Button three = new Button();
        GridPane.setConstraints(three,10,13);
        three.setText("3");
        three.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"3");
        }
        else input.setText("3");
        });
        three.setFont(new Font("Arial", 30));

        Button four = new Button();
        four.setText("4");
        GridPane.setConstraints(four,0,10);
        four.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"4");
        }
        else input.setText("4");
        });
        four.setFont(new Font("Arial", 30));

        Button five = new Button();
        GridPane.setConstraints(five,5,10);
        five.setText("5");
        five.setFont(new Font("Arial", 30));

        five.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"5");
        }
        else input.setText("5");
        });
        Button six = new Button();
        six.setFont(new Font("Arial", 30));

        GridPane.setConstraints(six,10,10);
        six.setText("6");
        six.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"6");
        }
        else input.setText("6");
        });
        Button seven = new Button();
        GridPane.setConstraints(seven,0,7);
        seven.setText("7");
        seven.setFont(new Font("Arial", 30));
        seven.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"7");
        }
        else input.setText("7");
        });
        Button eight = new Button();
        GridPane.setConstraints(eight,5,7);
        eight.setText("8");
        eight.setOnAction(e->{
        if(!input.getText().equals("0"))
        {
            input.setText(input.getText()+"8");
        }
        else input.setText("8");
        });
        eight.setFont(new Font("Arial", 30));
        Button nine = new Button();
        GridPane.setConstraints(nine,10,7);
        nine.setFont(new Font("Arial", 30));
        nine.setText("9");
        nine.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"9");
        }
        else input.setText("9");
        });

        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setHgap(10);
        layout.setVgap(12);

        layout.getChildren().addAll(input, equal, add, divide, subtrac, multiply, clearALL, del, point, zero, one, two, three, four, five, six, seven, eight, nine);
        Scene scene=new Scene(layout,450,550);
        layout.setStyle("-fx-background-color: #111213");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean decimal_checker(String text) {
        int c=0;
        boolean a=false;
        if(text.contains("+")||text.contains("-")||text.contains("X")||text.contains("/"))
        {
            for(int i=0;i<text.length();i++)
            {
                if(text.charAt(i)=='+'||text.charAt(i)=='-'||text.charAt(i)=='X'||text.charAt(i)=='/')
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
                    c++;
                }
                if(c==1)
                    return false;
            }
        }
        return true;
    }


}
