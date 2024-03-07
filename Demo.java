import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.*;

interface Observable {
    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers(String str);

    public void sendMsg(String str);

    public void sendPrivateMsg(String str, int num);

    public void commandFire();

}

interface Observer {

    public void notifyMe(Observable observable, String str);

    public void getMsg(Observable observable, String str);
}

class MainController extends JFrame implements Observable, ChangeListener {
    private JRadioButton clearBtn, privacyBtn;
    private JLabel clearAreaTxt, privacyTxt, soldierCount, fuelAmount, fuelAmountCount, ammoAmount, soldierCountTxt,
            deadSoldier;
    JLabel deadSoliderCount;
    private JTextArea recievedMsg, notifyArea;
    private JButton sendBtn;
    JButton supplyBtn;
    private JComboBox comboBox, comboBox2, comboBox3;
    private JTextField mainMsg;
    JSlider rangeBar;
    private ArrayList<Observer> observers;
    Helicopter helicopter = new Helicopter(this);
    Tank tank = new Tank(this);
    Submarine submarine = new Submarine(this);

    public void getRecievedMsg(String str) {
        int line = recievedMsg.getLineCount();
        recievedMsg.append(line + str + "\n");
    }

    public void getNotification(String str) {
        int line = notifyArea.getLineCount();
        notifyArea.append(line + str + "\n");
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String str) {
        for (Observer observer : observers) {
            observer.notifyMe(this, str);
        }
    }

    public void sendMsg(String str) {
        for (Observer observer : observers) {
            observer.getMsg(this, str);
        }
    }

    public void sendPrivateMsg(String str, int num) {
        observers.get(num).getMsg(this, str);
    }

    public void commandFire() {

        if (rangeBar.getValue() >= 20 && rangeBar.getValue() < 40) {
            if (helicopter.clearBtn.isSelected()) {
                helicopter.shoot.setEnabled(true);
                helicopter.missle.setEnabled(false);
            }
            if (tank.clearBtn.isSelected()) {
                tank.shoot.setEnabled(true);
                tank.missle.setEnabled(false);
            }
            if (submarine.clearBtn.isSelected()) {
                submarine.shoot.setEnabled(true);
                submarine.sonar.setEnabled(false);
            }
        }
        if (rangeBar.getValue() >= 40 && rangeBar.getValue() < 60) {
            if (helicopter.clearBtn.isSelected()) {
                helicopter.missle.setEnabled(true);
                helicopter.shoot.setEnabled(true);
                helicopter.laser.setEnabled(false);
            }
            if (tank.clearBtn.isSelected()) {
                tank.missle.setEnabled(true);
                tank.shoot.setEnabled(true);
                tank.redar.setEnabled(false);
            }
            if (submarine.clearBtn.isSelected()) {
                submarine.sonar.setEnabled(true);
                submarine.shoot.setEnabled(true);
                submarine.tomahawk.setEnabled(false);
            }

        }
        if (rangeBar.getValue() >= 60 && rangeBar.getValue() < 80) {
            if (helicopter.clearBtn.isSelected()) {
                helicopter.laser.setEnabled(true);
                helicopter.missle.setEnabled(true);
                helicopter.shoot.setEnabled(true);
            }
            if (tank.clearBtn.isSelected()) {
                tank.redar.setEnabled(true);
                tank.missle.setEnabled(true);
                tank.shoot.setEnabled(true);
                tank.rotateShoot.setEnabled(false);
            }
            if (submarine.clearBtn.isSelected()) {
                submarine.tomahawk.setEnabled(true);
                submarine.sonar.setEnabled(true);
                submarine.shoot.setEnabled(true);
                submarine.trident.setEnabled(false);
            }

        }
        if (rangeBar.getValue() >= 80 && rangeBar.getValue() < 90) {
            if (helicopter.clearBtn.isSelected()) {
                helicopter.laser.setEnabled(true);
                helicopter.missle.setEnabled(true);
                helicopter.shoot.setEnabled(true);
            }
            if (tank.clearBtn.isSelected()) {
                tank.redar.setEnabled(true);
                tank.missle.setEnabled(true);
                tank.shoot.setEnabled(true);
                tank.rotateShoot.setEnabled(true);
            }
            if (submarine.clearBtn.isSelected()) {
                submarine.tomahawk.setEnabled(true);
                submarine.sonar.setEnabled(true);
                submarine.shoot.setEnabled(true);
                submarine.trident.setEnabled(true);
                submarine.topido.setEnabled(false);
            }

        }
        if (rangeBar.getValue() >= 90) {
            if (helicopter.clearBtn.isSelected()) {
                helicopter.laser.setEnabled(true);
                helicopter.missle.setEnabled(true);
                helicopter.shoot.setEnabled(true);
            }
            if (tank.clearBtn.isSelected()) {
                tank.redar.setEnabled(true);
                tank.missle.setEnabled(true);
                tank.shoot.setEnabled(true);
                tank.rotateShoot.setEnabled(true);
            }
            if (submarine.clearBtn.isSelected()) {
                submarine.tomahawk.setEnabled(true);
                submarine.sonar.setEnabled(true);
                submarine.shoot.setEnabled(true);
                submarine.trident.setEnabled(true);
                submarine.topido.setEnabled(true);
            }
        }
        if (rangeBar.getValue() < 20) {
            helicopter.shoot.setEnabled(false);
            helicopter.laser.setEnabled(false);
            helicopter.missle.setEnabled(false);

            tank.redar.setEnabled(false);
            tank.missle.setEnabled(false);
            tank.shoot.setEnabled(false);
            tank.rotateShoot.setEnabled(false);

            submarine.tomahawk.setEnabled(false);
            submarine.sonar.setEnabled(false);
            submarine.shoot.setEnabled(false);
            submarine.trident.setEnabled(false);
            submarine.topido.setEnabled(false);
        }

    }

    MainController() {
        observers = new ArrayList<>();
        observers.add(helicopter);
        observers.add(tank);
        observers.add(submarine);

        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Main Camp");
        setLocation(0, 0);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 800, 400);
        add(mainPanel);

        clearBtn = new JRadioButton();
        clearBtn.setBounds(600, 30, 20, 20);
        mainPanel.add(clearBtn);

        clearAreaTxt = new JLabel("Area Clear");
        clearAreaTxt.setBounds(620, 25, 100, 30);
        clearAreaTxt.setFont(new Font("", 1, 15));
        mainPanel.add(clearAreaTxt);

        String[] statusArr = { "Helicopter", "Tank", "Submarine" };
        comboBox = new JComboBox(statusArr);
        comboBox.setFont(new Font("Arial", 1, 12));
        comboBox.setBounds(555, 185, 120, 24);
        comboBox.setEnabled(false);
        mainPanel.add(comboBox);

        privacyBtn = new JRadioButton();
        privacyBtn.setBounds(550, 160, 20, 20);
        mainPanel.add(privacyBtn);

        rangeBar = new JSlider(0, 100, 0);
        rangeBar.setBounds(490, 50, 50, 290);
        rangeBar.setPaintTicks(true);
        rangeBar.setMajorTickSpacing(20);
        rangeBar.setPaintTrack(true);
        rangeBar.setMinorTickSpacing(10);
        rangeBar.setPaintLabels(true);
        rangeBar.addChangeListener(this);
        rangeBar.setOrientation(SwingConstants.VERTICAL);
        mainPanel.add(rangeBar);

        soldierCount = new JLabel("Soldier Count :-");
        soldierCount.setBounds(30, 40, 100, 30);
        soldierCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCount);

        fuelAmount = new JLabel("Fuel Amount :-");
        fuelAmount.setBounds(30, 65, 100, 30);
        fuelAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmount);

        soldierCountTxt = new JLabel("25");
        soldierCountTxt.setBounds(170, 40, 100, 30);
        soldierCountTxt.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCountTxt);

        fuelAmountCount = new JLabel("2000");
        fuelAmountCount.setBounds(170, 65, 100, 30);
        fuelAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmountCount);

        JLabel ammoAmountCount = new JLabel("14000");
        ammoAmountCount.setBounds(170, 90, 110, 30);
        ammoAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmountCount);

        ammoAmount = new JLabel("Ammo Amount :-");
        ammoAmount.setBounds(30, 90, 110, 30);
        ammoAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmount);

        deadSoldier = new JLabel("Dead Soldier :-");
        deadSoldier.setBounds(30, 115, 160, 30);
        deadSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadSoldier);

        deadSoliderCount = new JLabel("0");
        deadSoliderCount.setBounds(170, 115, 160, 30);
        deadSoliderCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadSoliderCount);

        privacyTxt = new JLabel("Send Private");
        privacyTxt.setBounds(570, 155, 100, 30);
        privacyTxt.setFont(new Font("", 1, 15));
        mainPanel.add(privacyTxt);

        mainMsg = new JTextField();

        mainMsg.setBounds(550, 125, 200, 30);
        mainPanel.add(mainMsg);

        sendBtn = new JButton("Send");
        sendBtn.setBounds(680, 165, 70, 45);
        sendBtn.setFont(new Font("", 1, 12));
        mainPanel.add(sendBtn);

        recievedMsg = new JTextArea(60, 100);
        JScrollPane subPane = new JScrollPane(recievedMsg);
        subPane.setBounds(550, 230, 200, 100);
        mainPanel.add(subPane);

        String[] statusArr2 = { "Ammo", "Fuel" };
        comboBox2 = new JComboBox(statusArr2);
        comboBox2.setFont(new Font("Arial", 1, 12));
        comboBox2.setBounds(10, 165, 120, 24);
        mainPanel.add(comboBox2);

        comboBox3 = new JComboBox(statusArr);
        comboBox3.setFont(new Font("Arial", 1, 12));
        comboBox3.setBounds(140, 165, 120, 24);
        mainPanel.add(comboBox3);

        supplyBtn = new JButton("Supply");
        supplyBtn.setBounds(270, 165, 100, 24);
        supplyBtn.setFont(new Font("", 1, 12));
        mainPanel.add(supplyBtn);

        notifyArea = new JTextArea(60, 100);
        JScrollPane notifyPane = new JScrollPane(notifyArea);
        notifyPane.setBounds(10, 200, 465, 150);
        mainPanel.add(notifyPane);

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (clearBtn.isSelected()) {
                    if (rangeBar.getValue() >= 20) {
                        sendMsg("Area is clear, STOP shooting..");
                    }
                    notifyObservers("Area Clear");
                    rangeBar.setEnabled(false);
                    rangeBar.setValue(0);

                } else {
                    notifyObservers("Area Not Clear");
                    sendMsg("Area is not clear, READY to ATTACK..");
                    rangeBar.setEnabled(true);
                }
            }
        });
        supplyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (Integer.parseInt(helicopter.ammoAmountCount.getText()) < 4000 && comboBox2.getSelectedIndex() == 0
                        && comboBox3.getSelectedIndex() == 0) {
                    helicopter.ammoAmountCount.setText("4000");
                    helicopter.ammo1=true;
                    helicopter.ammo2=true;
                    sendPrivateMsg("You got a full supply of AMMO", comboBox3.getSelectedIndex());
                } else if (Integer.parseInt(tank.ammoAmountCount.getText()) < 5000 && comboBox2.getSelectedIndex() == 0
                        && comboBox3.getSelectedIndex() == 1) {
                    tank.ammoAmountCount.setText("5000");
                    tank.ammo1=true;
                    tank.ammo2=true;
                    sendPrivateMsg("You got a full supply of AMMO", comboBox3.getSelectedIndex());
                } else if (Integer.parseInt(submarine.ammoAmountCount.getText()) < 5000
                        && comboBox2.getSelectedIndex() == 0 && comboBox3.getSelectedIndex() == 2) {
                    submarine.ammoAmountCount.setText("5000");
                    submarine.ammo1=true;
                    submarine.ammo2=true;
                    sendPrivateMsg("You got a full supply of AMMO", comboBox3.getSelectedIndex());
                } else {
                    if (helicopter.ammoAmountCount.getText().equals("4000")  && comboBox3.getSelectedIndex() == 0 && comboBox2.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "The helicopter's ammo is full..", "Ammo full.!!!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (tank.ammoAmountCount.getText().equals("5000")   && comboBox3.getSelectedIndex() == 1 && comboBox2.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "The tank's ammo is full..", "Ammo full.!!!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (submarine.ammoAmountCount.getText().equals("5000")  && comboBox3.getSelectedIndex() == 2 && comboBox2.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "The submarine's ammo is full..", "Ammo full.!!!",
                                JOptionPane.INFORMATION_MESSAGE);

                    }

                }
                if (Integer.parseInt(helicopter.fuelAmountCount.getText()) < 600 && comboBox2.getSelectedIndex() == 1
                        && comboBox3.getSelectedIndex() == 0) {
                    helicopter.fuelAmountCount.setText("600");
                    helicopter.ammo1=true;
                    helicopter.ammo2=true;
                    sendPrivateMsg("You got a full supply of FUEL", comboBox3.getSelectedIndex());
                } else if (Integer.parseInt(tank.fuelAmountCount.getText()) < 600 && comboBox2.getSelectedIndex() == 1
                        && comboBox3.getSelectedIndex() == 1) {
                    tank.fuelAmountCount.setText("600");
                    tank.ammo1=true;
                    tank.ammo2=true;
                    sendPrivateMsg("You got a full supply of FUEL", comboBox3.getSelectedIndex());
                } else if (Integer.parseInt(submarine.fuelAmountCount.getText()) < 800
                        && comboBox2.getSelectedIndex() == 1 && comboBox3.getSelectedIndex() == 2) {
                    submarine.fuelAmountCount.setText("800");
                    submarine.ammo1=true;
                    submarine.ammo2=true;
                    sendPrivateMsg("You got a full supply of FUEL", comboBox3.getSelectedIndex());
                } else {
                    if (helicopter.fuelAmountCount.getText().equals("600")  && comboBox3.getSelectedIndex() == 0 && comboBox2.getSelectedIndex() == 1) {
                        JOptionPane.showMessageDialog(null, "The helicopter's fuel is full..", "Fuel full.!!!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (tank.fuelAmountCount.getText().equals("600")   && comboBox3.getSelectedIndex() == 1 && comboBox2.getSelectedIndex() == 1) {
                        JOptionPane.showMessageDialog(null, "The tank's fuel is full..", "Fuel full.!!!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (submarine.fuelAmountCount.getText().equals("800")  && comboBox3.getSelectedIndex() == 2 && comboBox2.getSelectedIndex() == 1) {
                        JOptionPane.showMessageDialog(null, "The submarine's fuel is full..", "Fuel full.!!!",
                                JOptionPane.INFORMATION_MESSAGE);

                    }

                }
            }
        });

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!mainMsg.getText().equals("") && !privacyBtn.isSelected()) {
                    sendMsg(mainMsg.getText());
                    mainMsg.setText("");
                } else if (!mainMsg.getText().equals("") && privacyBtn.isSelected()) {
                    sendPrivateMsg(mainMsg.getText(), comboBox.getSelectedIndex());
                    mainMsg.setText("");
                }
            }
        });

        privacyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (privacyBtn.isSelected()) {
                    comboBox.setEnabled(true);
                } else {
                    comboBox.setEnabled(false);
                }
            }
        });

    }

    public void stateChanged(ChangeEvent e) {
        commandFire();
    }
}

class Helicopter extends JFrame implements Observer, KeyListener {
    JRadioButton clearBtn;
    private JLabel positionTxt, clearTxt, health, soldierCount, activeSoldier, fuelAmount, ammoAmount,
            activeSoliderCount,
            deadSoldier;
    JLabel fuelAmountCount, ammoAmountCount;
    private JTextArea recievedMsg;
    private JTextField msgField, deadCount;
    JButton shoot, missle, laser, sendBtn;
    private JSlider rangeBar;
    MainController m;
    boolean ammo1, ammo2;

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        try {
            if (Integer.parseInt(activeSoliderCount.getText()) >= Integer.parseInt(deadCount.getText())) {
                int num = Integer.parseInt(activeSoliderCount.getText()) - Integer.parseInt(deadCount.getText());
                activeSoliderCount.setText(Integer.toString(num));
                deadCount.setBorder(null);
                int mainDeadCount = Integer.parseInt(deadCount.getText())
                        + Integer.parseInt(m.deadSoliderCount.getText());
                m.deadSoliderCount.setText(Integer.toString(mainDeadCount));
                m.getNotification(". " + deadCount.getText() + " Soldiers down in Helicopter..NEED MEDICAL BACKUP");

                if (mainDeadCount == 25) {
                    m.supplyBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "The entire army was fallen out..Mission aborted..Fall back",
                            "Mission Failed.!!!",
                            JOptionPane.ERROR_MESSAGE);
                          
                }
            } else {
                deadCount.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (Integer.parseInt(activeSoliderCount.getText()) == 0) {
                deadCount.setText(" All Man Down");
                deadCount.setEnabled(false);
                rangeBar.setValue(0);
                rangeBar.setEnabled(false);
                sendBtn.setEnabled(false);
                m.getNotification(". The Helicopter CRASHED..We LOST the helicopter..OVER !!!");

            }
        } catch (Exception ev) {
            deadCount.setBorder(BorderFactory.createLineBorder(Color.RED));
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void notifyMe(Observable observable, String str) {
        clearTxt.setText(str);
    }

    public void getMsg(Observable observable, String str) {
        int line = recievedMsg.getLineCount();
        recievedMsg.append(line + ". Alpha: " + str + "\n");
    }

    public Helicopter(MainController m) {
        ammo1 = true;
        ammo2 = true;
        this.m = m;
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Helicopter");
        setLocation(790, 0);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 800, 400);
        add(mainPanel);

        clearBtn = new JRadioButton();
        clearBtn.setBounds(30, 300, 20, 30);
        mainPanel.add(clearBtn);

        positionTxt = new JLabel("Positioned");
        positionTxt.setBounds(50, 300, 150, 30);
        positionTxt.setFont(new Font("", 1, 15));
        mainPanel.add(positionTxt);

        health = new JLabel("Health");
        health.setBounds(680, 7, 100, 30);
        health.setFont(new Font("", 1, 15));
        mainPanel.add(health);

        rangeBar = new JSlider(0, 100, 100);
        rangeBar.setBounds(690, 40, 50, 290);
        rangeBar.setPaintTicks(true);
        rangeBar.setMajorTickSpacing(20);
        rangeBar.setPaintTrack(true);
        rangeBar.setMinorTickSpacing(10);
        rangeBar.setPaintLabels(true);
        rangeBar.setOrientation(SwingConstants.VERTICAL);
        mainPanel.add(rangeBar);

        clearTxt = new JLabel("Area Not Cleared");
        clearTxt.setBounds(15, 10, 200, 30);
        clearTxt.setForeground(Color.RED);
        clearTxt.setFont(new Font("Arial", 1, 15));
        mainPanel.add(clearTxt);

        soldierCount = new JLabel("Soldier Count :-");
        soldierCount.setBounds(30, 40, 100, 30);
        soldierCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCount);

        JLabel soldierCountTxt = new JLabel("8");
        soldierCountTxt.setBounds(170, 40, 100, 30);
        soldierCountTxt.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCountTxt);

        fuelAmountCount = new JLabel("600");
        fuelAmountCount.setBounds(170, 65, 100, 30);
        fuelAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmountCount);

        ammoAmountCount = new JLabel("4000");
        ammoAmountCount.setBounds(170, 90, 110, 30);
        ammoAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmountCount);

        fuelAmount = new JLabel("Fuel Amount :-");
        fuelAmount.setBounds(30, 65, 100, 30);
        fuelAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmount);

        ammoAmount = new JLabel("Ammo Amount :-");
        ammoAmount.setBounds(30, 90, 110, 30);
        ammoAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmount);

        activeSoldier = new JLabel("Active Soldier Count :-");
        activeSoldier.setBounds(30, 115, 160, 30);
        activeSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(activeSoldier);

        activeSoliderCount = new JLabel("8");
        activeSoliderCount.setBounds(175, 115, 160, 30);
        activeSoliderCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(activeSoliderCount);

        deadSoldier = new JLabel("Dead Soldier :-");
        deadSoldier.setBounds(30, 140, 160, 30);
        deadSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadSoldier);

        deadCount = new JTextField();
        deadCount.addKeyListener(this);
        deadCount.setBounds(130, 140, 100, 30);
        deadCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadCount);

        shoot = new JButton("Shoot");
        shoot.setBounds(30, 180, 150, 30);
        shoot.setEnabled(false);
        shoot.setFont(new Font("Arial", 1, 15));
        mainPanel.add(shoot);

        missle = new JButton("Missile");
        missle.setBounds(30, 220, 150, 30);
        missle.setEnabled(false);
        missle.setFont(new Font("Arial", 1, 15));
        mainPanel.add(missle);

        laser = new JButton("Laser");
        laser.setBounds(30, 260, 150, 30);
        laser.setEnabled(false);
        laser.setFont(new Font("Arial", 1, 15));
        mainPanel.add(laser);

        recievedMsg = new JTextArea(60, 100);
        JScrollPane subPane = new JScrollPane(recievedMsg);
        subPane.setBounds(300, 30, 350, 250);
        mainPanel.add(subPane);

        msgField = new JTextField();
        msgField.setBounds(300, 290, 350, 30);
        mainPanel.add(msgField);

        sendBtn = new JButton("Send");
        sendBtn.setBounds(550, 323, 100, 30);
        sendBtn.setFont(new Font("Arial", 1, 15));
        mainPanel.add(sendBtn);

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!msgField.getText().equals("")) {
                    m.getRecievedMsg(". Helicopter: " + msgField.getText());
                    msgField.setText("");
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (clearBtn.isSelected()) {
                    m.getNotification(". Helicopter was positioned");

                } else {
                    m.getNotification(". Helicopter was out of positioned");
                    shoot.setEnabled(false);
                    missle.setEnabled(false);
                    laser.setEnabled(false);
                }
            }
        });

        shoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 0) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The HELICOPTER is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2000 && ammo1 == true) {
                    m.getNotification(
                            ". The helicopter's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 100;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        missle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 200) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The HELICOPTER is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2000 && ammo1 == true) {
                    m.getNotification(
                            ". The helicopter's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 200;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        laser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 300) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The HELICOPTER is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2000 && ammo1 == true) {
                    m.getNotification(
                            ". The helicopter's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 300;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });

    }
}

class Tank extends JFrame implements Observer, KeyListener {
    JRadioButton clearBtn;
    private JLabel positionTxt, clearTxt, health, soldierCount, activeSoldier, fuelAmount, ammoAmount,
            activeSoliderCount,
            deadSoldier;
    private JTextArea recievedMsg;
    JLabel fuelAmountCount, ammoAmountCount;
    private JTextField msgField, deadCount;
    JButton shoot, missle, redar, rotateShoot, sendBtn;
    private JSlider rangeBar;
    MainController m;
    boolean ammo1, ammo2;

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        try {
            if (Integer.parseInt(activeSoliderCount.getText()) >= Integer.parseInt(deadCount.getText())) {
                int num = Integer.parseInt(activeSoliderCount.getText()) - Integer.parseInt(deadCount.getText());
                activeSoliderCount.setText(Integer.toString(num));
                deadCount.setBorder(null);
                int mainDeadCount = Integer.parseInt(deadCount.getText())
                        + Integer.parseInt(m.deadSoliderCount.getText());
                m.deadSoliderCount.setText(Integer.toString(mainDeadCount));
                m.getNotification(". " + deadCount.getText() + " Soldiers down in Tank..NEED MEDICAL BACKUP");
                if (mainDeadCount == 25) {
                    m.supplyBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "The entire army was fallen out..Mission aborted..Fall back",
                            "Mission Failed.!!!",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                deadCount.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (Integer.parseInt(activeSoliderCount.getText()) == 0) {
                deadCount.setText(" All Man Down");
                sendBtn.setEnabled(false);
                m.getNotification(". The Tank DESTROYED..We LOST the Tank..OVER !!!");
                deadCount.setEnabled(false);
                rangeBar.setValue(0);
                rangeBar.setEnabled(false);
            }
        } catch (Exception ev) {
            deadCount.setBorder(BorderFactory.createLineBorder(Color.RED));
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void notifyMe(Observable observable, String str) {
        clearTxt.setText(str);
    }

    public void getMsg(Observable observable, String str) {
        int line = recievedMsg.getLineCount();
        recievedMsg.append(line + ". Alpha: " + str + "\n");
    }

    Tank(MainController m) {
        ammo1 = true;
        ammo2 = true;
        this.m = m;
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setLocation(790, 400);
        setTitle("Tank");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 800, 400);
        add(mainPanel);

        activeSoliderCount = new JLabel("5");
        activeSoliderCount.setBounds(175, 115, 160, 30);
        activeSoliderCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(activeSoliderCount);

        clearBtn = new JRadioButton();
        clearBtn.setBounds(30, 300, 20, 30);
        mainPanel.add(clearBtn);

        positionTxt = new JLabel("Positioned");
        positionTxt.setBounds(50, 300, 150, 30);
        positionTxt.setFont(new Font("", 1, 15));
        mainPanel.add(positionTxt);

        JLabel soldierCountTxt = new JLabel("5");
        soldierCountTxt.setBounds(170, 40, 100, 30);
        soldierCountTxt.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCountTxt);

        fuelAmountCount = new JLabel("600");
        fuelAmountCount.setBounds(170, 65, 100, 30);
        fuelAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmountCount);

        ammoAmountCount = new JLabel("5000");
        ammoAmountCount.setBounds(170, 90, 110, 30);
        ammoAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmountCount);

        health = new JLabel("Health");
        health.setBounds(680, 7, 100, 30);
        health.setFont(new Font("", 1, 15));
        mainPanel.add(health);

        rangeBar = new JSlider(0, 100, 100);
        rangeBar.setBounds(690, 40, 50, 290);
        rangeBar.setPaintTicks(true);
        rangeBar.setMajorTickSpacing(20);
        rangeBar.setPaintTrack(true);
        rangeBar.setMinorTickSpacing(10);
        rangeBar.setPaintLabels(true);
        rangeBar.setOrientation(SwingConstants.VERTICAL);
        mainPanel.add(rangeBar);

        clearTxt = new JLabel("Area Not Cleared");
        clearTxt.setBounds(15, 10, 200, 30);
        clearTxt.setForeground(Color.RED);
        clearTxt.setFont(new Font("Arial", 1, 15));
        mainPanel.add(clearTxt);

        soldierCount = new JLabel("Soldier Count :-");
        soldierCount.setBounds(30, 40, 100, 30);
        soldierCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCount);

        fuelAmount = new JLabel("Fuel Amount :-");
        fuelAmount.setBounds(30, 65, 100, 30);
        fuelAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmount);

        ammoAmount = new JLabel("Ammo Amount :-");
        ammoAmount.setBounds(30, 90, 110, 30);
        ammoAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmount);

        activeSoldier = new JLabel("Active Soldier Count :-");
        activeSoldier.setBounds(30, 115, 160, 30);
        activeSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(activeSoldier);

        deadSoldier = new JLabel("Dead Soldier :-");
        deadSoldier.setBounds(30, 140, 160, 30);
        deadSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadSoldier);

        deadCount = new JTextField();
        deadCount.setBounds(130, 140, 100, 30);
        deadCount.addKeyListener(this);
        deadCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadCount);

        shoot = new JButton("Shoot");
        shoot.setBounds(30, 180, 120, 30);
        shoot.setEnabled(false);
        shoot.setFont(new Font("Arial", 1, 15));
        mainPanel.add(shoot);

        missle = new JButton("Missile");
        missle.setBounds(160, 180, 120, 30);
        missle.setEnabled(false);
        missle.setFont(new Font("Arial", 1, 15));
        mainPanel.add(missle);

        redar = new JButton("Redar");
        redar.setBounds(30, 220, 120, 30);
        redar.setEnabled(false);
        redar.setFont(new Font("Arial", 1, 15));
        mainPanel.add(redar);

        rotateShoot = new JButton("Rotate Shoot");
        rotateShoot.setBounds(160, 220, 120, 30);
        rotateShoot.setEnabled(false);
        rotateShoot.setFont(new Font("Arial", 1, 12));
        mainPanel.add(rotateShoot);

        recievedMsg = new JTextArea(60, 100);
        JScrollPane subPane = new JScrollPane(recievedMsg);
        subPane.setBounds(300, 30, 350, 250);
        mainPanel.add(subPane);

        msgField = new JTextField();
        msgField.setBounds(300, 290, 350, 30);
        mainPanel.add(msgField);

        sendBtn = new JButton("Send");
        sendBtn.setBounds(550, 323, 100, 30);
        sendBtn.setFont(new Font("Arial", 1, 15));
        mainPanel.add(sendBtn);

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!msgField.getText().equals("")) {
                    m.getRecievedMsg(". Tank: " + msgField.getText());
                    msgField.setText("");
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (clearBtn.isSelected()) {
                    m.getNotification(". Tank was positioned");
                } else {
                    shoot.setEnabled(false);
                    missle.setEnabled(false);
                    redar.setEnabled(false);
                    rotateShoot.setEnabled(false);
                    m.getNotification(". Tank was out of positioned");
                }
            }
        });

        shoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 0) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The TANK is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The tank's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 50;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        missle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 150) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The TANK is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The tank's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 150;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        redar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 200) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The TANK is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The tank's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 200;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        rotateShoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 300) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The TANK is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The tank's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 300;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });

    }
}

class Submarine extends JFrame implements Observer, KeyListener {
    JRadioButton clearBtn;
    private JLabel positionTxt, clearTxt, energy, soldierCount, activeSoldier, fuelAmount, ammoAmount, oxygen,
            activeSoliderCount,
            deadSoldier;
    private JTextArea recievedMsg;
    JLabel fuelAmountCount, ammoAmountCount;
    private JTextField msgField, deadCount;
    JButton shoot, sonar, tomahawk, trident, topido, sendBtn;
    private JSlider rangeBar, oxygenBar;
    MainController m;
    boolean ammo1, ammo2;

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        try {
            if (Integer.parseInt(activeSoliderCount.getText()) >= Integer.parseInt(deadCount.getText())) {
                int num = Integer.parseInt(activeSoliderCount.getText()) - Integer.parseInt(deadCount.getText());
                activeSoliderCount.setText(Integer.toString(num));
                deadCount.setBorder(null);
                int mainDeadCount = Integer.parseInt(deadCount.getText())
                        + Integer.parseInt(m.deadSoliderCount.getText());
                m.deadSoliderCount.setText(Integer.toString(mainDeadCount));
                m.getNotification(". " + deadCount.getText() + " Soldiers down in Submarine..NEED MEDICAL BACKUP");
                if (mainDeadCount == 25) {
                    m.supplyBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "The entire army was fallen out..Mission aborted..Fall back",
                            "Mission Failed.!!!",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                deadCount.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (Integer.parseInt(activeSoliderCount.getText()) == 0) {
                deadCount.setText(" All Man Down");
                sendBtn.setEnabled(false);
                deadCount.setEnabled(false);
                m.getNotification(". The Submarine SANK..We LOST the Submarine..OVER !!!");
                rangeBar.setValue(0);
                oxygenBar.setValue(0);
                oxygenBar.setEnabled(false);
                rangeBar.setEnabled(false);
            }
        } catch (Exception ev) {
            deadCount.setBorder(BorderFactory.createLineBorder(Color.RED));
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void notifyMe(Observable observable, String str) {
        clearTxt.setText(str);
    }

    public void getMsg(Observable observable, String str) {
        int line = recievedMsg.getLineCount();
        recievedMsg.append(line + ". Alpha: " + str + "\n");
    }

    Submarine(MainController m) {
        ammo1 = true;
        ammo2 = true;
        this.m = m;
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setLocation(0, 400);
        setTitle("Submarine");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 800, 400);
        add(mainPanel);

        clearBtn = new JRadioButton();
        clearBtn.setBounds(30, 300, 20, 30);
        mainPanel.add(clearBtn);

        activeSoliderCount = new JLabel("12");
        activeSoliderCount.setBounds(175, 115, 160, 30);
        activeSoliderCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(activeSoliderCount);

        positionTxt = new JLabel("Positioned");
        positionTxt.setBounds(50, 300, 150, 30);
        positionTxt.setFont(new Font("", 1, 15));
        mainPanel.add(positionTxt);

        JLabel soldierCountTxt = new JLabel("12");
        soldierCountTxt.setBounds(170, 40, 100, 30);
        soldierCountTxt.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCountTxt);

        fuelAmountCount = new JLabel("800");
        fuelAmountCount.setBounds(170, 65, 100, 30);
        fuelAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmountCount);

        ammoAmountCount = new JLabel("5000");
        ammoAmountCount.setBounds(170, 90, 110, 30);
        ammoAmountCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmountCount);

        energy = new JLabel("Energy");
        energy.setBounds(620, 7, 100, 30);
        energy.setFont(new Font("", 1, 13));
        mainPanel.add(energy);

        oxygen = new JLabel("Oxigen");
        oxygen.setBounds(700, 7, 100, 30);
        oxygen.setFont(new Font("", 1, 13));
        mainPanel.add(oxygen);

        rangeBar = new JSlider(0, 100, 100);
        rangeBar.setBounds(710, 40, 50, 290);
        rangeBar.setPaintTicks(true);
        rangeBar.setMajorTickSpacing(20);
        rangeBar.setPaintTrack(true);
        rangeBar.setMinorTickSpacing(10);
        rangeBar.setPaintLabels(true);
        rangeBar.setOrientation(SwingConstants.VERTICAL);
        mainPanel.add(rangeBar);

        oxygenBar = new JSlider(0, 100, 100);
        oxygenBar.setBounds(630, 40, 50, 290);
        oxygenBar.setPaintTicks(true);
        oxygenBar.setMajorTickSpacing(20);
        oxygenBar.setPaintTrack(true);
        oxygenBar.setMinorTickSpacing(10);
        oxygenBar.setPaintLabels(true);
        oxygenBar.setOrientation(SwingConstants.VERTICAL);
        mainPanel.add(oxygenBar);

        clearTxt = new JLabel("Area Not Cleared");
        clearTxt.setBounds(15, 10, 200, 30);
        clearTxt.setForeground(Color.RED);
        clearTxt.setFont(new Font("Arial", 1, 15));
        mainPanel.add(clearTxt);

        soldierCount = new JLabel("Soldier Count :-");
        soldierCount.setBounds(30, 40, 100, 30);
        soldierCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCount);

        soldierCountTxt = new JLabel("12");
        soldierCountTxt.setBounds(170, 40, 100, 30);
        soldierCountTxt.setFont(new Font("Arial", 1, 13));
        mainPanel.add(soldierCountTxt);

        fuelAmount = new JLabel("Fuel Amount :-");
        fuelAmount.setBounds(30, 65, 100, 30);
        fuelAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(fuelAmount);

        ammoAmount = new JLabel("Ammo Amount :-");
        ammoAmount.setBounds(30, 90, 110, 30);
        ammoAmount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(ammoAmount);

        activeSoldier = new JLabel("Active Soldier Count :-");
        activeSoldier.setBounds(30, 115, 160, 30);
        activeSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(activeSoldier);

        deadSoldier = new JLabel("Dead Soldier :-");
        deadSoldier.setBounds(30, 140, 160, 30);
        deadSoldier.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadSoldier);

        deadCount = new JTextField();
        deadCount.setBounds(130, 140, 100, 30);
        deadCount.addKeyListener(this);
        deadCount.setFont(new Font("Arial", 1, 13));
        mainPanel.add(deadCount);

        shoot = new JButton("Shoot");
        shoot.setBounds(30, 180, 120, 30);
        shoot.setEnabled(false);
        shoot.setFont(new Font("Arial", 1, 15));
        mainPanel.add(shoot);

        sonar = new JButton("Sonar");
        sonar.setBounds(160, 180, 120, 30);
        sonar.setEnabled(false);
        sonar.setFont(new Font("Arial", 1, 15));
        mainPanel.add(sonar);

        tomahawk = new JButton("Tomahawk Missile");
        tomahawk.setBounds(30, 220, 120, 30);
        tomahawk.setEnabled(false);
        tomahawk.setFont(new Font("Arial", 1, 12));
        mainPanel.add(tomahawk);

        trident = new JButton("trident-2");
        trident.setBounds(160, 220, 120, 30);
        trident.setFont(new Font("Arial", 1, 12));
        trident.setEnabled(false);
        mainPanel.add(trident);

        topido = new JButton("Topido");
        topido.setBounds(30, 260, 250, 30);
        topido.setFont(new Font("Arial", 1, 15));
        topido.setEnabled(false);
        mainPanel.add(topido);

        recievedMsg = new JTextArea(60, 100);
        JScrollPane subPane = new JScrollPane(recievedMsg);
        subPane.setBounds(300, 30, 300, 250);
        mainPanel.add(subPane);

        msgField = new JTextField();
        msgField.setBounds(300, 290, 300, 30);
        mainPanel.add(msgField);

        sendBtn = new JButton("Send");
        sendBtn.setBounds(500, 323, 100, 30);
        sendBtn.setFont(new Font("Arial", 1, 15));
        mainPanel.add(sendBtn);

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!msgField.getText().equals("")) {
                    m.getRecievedMsg(". Submarine: " + msgField.getText());
                    msgField.setText("");
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (clearBtn.isSelected()) {
                    m.getNotification(". Submarine was positioned");

                } else {
                    m.getNotification(". Submarine was out of positioned");
                    shoot.setEnabled(false);
                    sonar.setEnabled(false);
                    tomahawk.setEnabled(false);
                    trident.setEnabled(false);
                    topido.setEnabled(false);

                }
            }
        });
        shoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 0) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The SUBMARINE is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The submarine's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 50;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        sonar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 150) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The SUBMARINE is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The submarine's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 150;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        tomahawk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 200) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The SUBMARINE is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The submarine's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 200;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        trident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 250) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The SUBMARINE is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The submarine's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 250;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });
        topido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int count = Integer.parseInt(ammoAmountCount.getText());
                if (count <= 300) {
                    ammoAmountCount.setText("0");

                    if (ammo2) {
                        m.getNotification(". The SUBMARINE is OUT OF AMMO..Provide an IMMEDIATE supply of AMMO");
                        ammo2 = false;
                    }
                    return;
                }
                int fuel = Integer.parseInt(fuelAmountCount.getText());
                if (fuel <= 0) {
                    fuelAmountCount.setText("0");
                    return;
                }
                if (count <= 2500 && ammo1 == true) {
                    m.getNotification(". The submarine's ammo capacity was reduced by 50%..ready to supply ammunition");
                    ammo1 = false;
                }
                fuelAmountCount.setText(Integer.toString(fuel -= 1));
                count = count - 300;
                ammoAmountCount.setText(Integer.toString(count));
            }
        });

    }
}

class Demo {
    public static void main(String[] args) {
        MainController m = new MainController();
        m.setVisible(true);
        m.helicopter.setVisible(true);
        m.tank.setVisible(true);
        m.submarine.setVisible(true);
    }
}