package University.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice cEmpID ;
    JTable table ;
    JButton search , print , add , update , cancel ;

    TeacherDetails(){

        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Search by Emp_ID Number") ;
        heading.setBounds(20 , 20 , 150 , 20);
        add(heading) ;

        cEmpID = new Choice();
        cEmpID.setBounds(180 , 20 , 150 ,20);
        add(cEmpID) ;

        try{
            Conn c = new Conn() ;
            ResultSet rs = c.s.executeQuery("select * from teacher") ;
            while (rs.next()){
                cEmpID.add(rs.getString("EmpID"));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

        table = new JTable() ;
        JScrollPane jsp = new JScrollPane(table) ;
        jsp.setBounds(0 , 100 , 900 , 600);
        add(jsp) ;


        try{
            Conn c = new Conn() ;
            ResultSet rs = c.s.executeQuery("select * from teacher") ;
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e ){
            e.printStackTrace();
        }


        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search) ;



        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print) ;



        add = new JButton("Add");
        add.setBounds(220,70,80,20);
        add.addActionListener(this);
        add(add) ;



        update = new JButton("Update");
        update.setBounds(320,70,80,20);
        update.addActionListener(this);
        add(update) ;



        cancel = new JButton("Cancel");
        cancel.setBounds(420,70,80,20);
        cancel.addActionListener(this);
        add(cancel) ;



        setSize(900 , 700);
        setLocation(300 , 100);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search)
        {
            String query = "select * from student where EmpID = '" + cEmpID.getSelectedItem() + "'" ;
            try{
                Conn c = new Conn() ;
                ResultSet rs = c.s.executeQuery(query) ;
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (Exception e){

            }
        } else if (ae.getSource() == print) {
            try {
                table.print() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddTeacher() ;
        }else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateTeacher() ;
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails() ;
    }
}
