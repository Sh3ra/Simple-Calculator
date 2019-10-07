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
    String res;
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
    Button Equal;
    Button Add;
    Button Subtrac;
    Button Multiply;
    Button Divide;
    Button One;
    Button Two;
    Button Three;
    Button Four;
    Button Five;
    Button Six;
    Button Seven;
    Button Eight;
    Button Nine;
    Button Zero;
    Button Point;
    Button ClearALL;
    Button Del;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calculator");
        Label input=new Label("0");
        input.setMinWidth(Region.USE_PREF_SIZE);
        input.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(input,0,1,16,1);
        input.setMaxWidth(450);
        input.setFont(new Font("Arial", 50));
        input.setTextFill(Color.web("#ffffff"));

        ClearALL =new Button("AC");
        ClearALL.setFont(new Font("Arial", 20));
        GridPane.setConstraints(ClearALL,0,4);
        ClearALL.setOnAction(e->input.setText("0"));
        Del=new Button("Del");
        Del.setFont(new Font("Arial", 20));
        GridPane.setConstraints(Del,5,4);
        Del.setOnAction(e->{
            if(input.getText()!="")
            {
                String temp="";
                for(int i=0;i<input.getText().length()-1;i++)
                {
                    temp=temp+input.getText().charAt(i);
                }
                if(temp=="")
                    temp+="0";
                input.setText(temp);
            }
        });
        Point = new Button(".");
        Point.setFont(new Font("Arial", 30));
        GridPane.setConstraints(Point,10,16);
        Point.setOnAction(e->{
            if(input.getText().charAt(input.getText().length()-1)=='+'||input.getText().charAt(input.getText().length()-1)=='-'||input.getText().charAt(input.getText().length()-1)=='X'||input.getText().charAt(input.getText().length()-1)=='/')
            {
                input.setText(input.getText()+"0.");
            }
            else if(decimal_checker(input.getText()))
                input.setText(input.getText()+".");
            else AlertBox.display("Mate","How many decimal points do you expect in a number!?");
        });
        Equal=new Button();
        GridPane.setConstraints(Equal,15,16);
        Equal.setText("=");
        Equal.setFont(new Font("Arial", 30));
        Equal.setOnAction(e -> {
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
        Add=new Button();
        Add.setFont(new Font("Arial", 30));
        GridPane.setConstraints(Add,15,13);
        Add.setText("+");
        Add.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0+");
                else input.setText(input.getText()+"+");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Subtrac=new Button();
        Subtrac.setFont(new Font("Arial", 30));
        GridPane.setConstraints(Subtrac,15,10);
        Subtrac.setText("-");
        Subtrac.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0-");
                else input.setText(input.getText()+"-");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Multiply=new Button();
        GridPane.setConstraints(Multiply,15,7);
        Multiply.setText("X");
        Multiply.setFont(new Font("Arial", 30));
        Multiply.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0X");
                else input.setText(input.getText()+"X");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Divide=new Button();
        GridPane.setConstraints(Divide,15,4);
        Divide.setText("/");
        Divide.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                if(input.getText().charAt(input.getText().length()-1)=='.')
                    input.setText(input.getText()+"0/");
                else input.setText(input.getText()+"/");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Divide.setFont(new Font("Arial", 30));
        Zero = new Button();
        Zero.setFont(new Font("Arial", 30));
        GridPane.setConstraints(Zero,5,16);
        Zero.setText("0");
        Zero.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"0");
        }
        });
        One = new Button();
        GridPane.setConstraints(One,0,13);
        One.setText("1");
        One.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"1");
        }
        else input.setText("1");
        });
        One.setFont(new Font("Arial", 30));

        Two = new Button();
        GridPane.setConstraints(Two,5,13);
        Two.setText("2");
        Two.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"2");
        }
        else input.setText("2");
        });
        Two.setFont(new Font("Arial", 30));

        Three= new Button();
        GridPane.setConstraints(Three,10,13);
        Three.setText("3");
        Three.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"3");
        }
        else input.setText("3");
        });
        Three.setFont(new Font("Arial", 30));

        Four = new Button();
        Four.setText("4");
        GridPane.setConstraints(Four,0,10);
        Four.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"4");
        }
        else input.setText("4");
        });
        Four.setFont(new Font("Arial", 30));

        Five = new Button();
        GridPane.setConstraints(Five,5,10);
        Five.setText("5");
        Five.setFont(new Font("Arial", 30));

        Five.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"5");
        }
        else input.setText("5");
        });
        Six = new Button();
        Six.setFont(new Font("Arial", 30));

        GridPane.setConstraints(Six,10,10);
        Six.setText("6");
        Six.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"6");
        }
        else input.setText("6");
        });
        Seven = new Button();
        GridPane.setConstraints(Seven,0,7);
        Seven.setText("7");
        Seven.setFont(new Font("Arial", 30));
        Seven.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"7");
        }
        else input.setText("7");
        });
        Eight = new Button();
        GridPane.setConstraints(Eight,5,7);
        Eight.setText("8");
        Eight.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"8");
        }
        else input.setText("8");
        });
        Eight.setFont(new Font("Arial", 30));
        Nine = new Button();
        GridPane.setConstraints(Nine,10,7);
        Nine.setFont(new Font("Arial", 30));
        Nine.setText("9");
        Nine.setOnAction(e->{
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

        layout.getChildren().addAll(input,Equal,Add,Divide,Subtrac,Multiply,ClearALL,Del,Point,Zero,One,Two,Three,Four,Five,Six,Seven,Eight,Nine);
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
