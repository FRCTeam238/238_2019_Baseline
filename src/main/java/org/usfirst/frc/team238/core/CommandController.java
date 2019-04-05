package org.usfirst.frc.team238.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//import org.usfirst.frc.team238.robot.AutonomousDrive;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.core.Logger;


public class CommandController {
    // Command factories
    AutonomousCmdFactory theRouge;
    DriverCommandFactory theDriverCommandFactory;
    OperatorCmdFactory theOperatorCmdFactory;

    // Command lists
    HashMap<String, Command> autoCmdList;
    HashMap<Integer, Command> driverCmdList;   
    HashMap<Integer, Command> operatorCmdList;
    HashMap<Integer, Command> driverLeftCmdList;
	HashMap<Integer, Command> driverRightCmdList;
    // keeping track of previous buttons
    HashMap<Integer, Integer[]> previousButtons;
    /**
     * Populates the command factories with their respective objects
     * 
     *
     * @param myRobot
     *
     */
    public void init(Robot myRobot) {

        setupOperatorCommands(myRobot);
        setupDriverCommands(myRobot);
        setupAutonomousCommands(myRobot);

    }

    /**
     * Gets an AutoCommand by key name
     * 
     * @param cmdName
     * @return
     */
    public Command getAutoCmd(String cmdName) {
        return autoCmdList.get(cmdName);
    }

    /**
     * Creates the autonomous command factory and gets the command list that's
     * created in that factory
     * 
     *
     * @param myRobot
     * 
     */

    private void setupAutonomousCommands(Robot myRobot) {
        theRouge = new AutonomousCmdFactory();
        theRouge.init();
        autoCmdList = theRouge.createAutonomousCommands(myRobot);

    }

    // gets a Driver Command by key name
    // public Command getDriverCmd(String cmdName) {
    //     return driverCmdList.get(cmdName);
    // }

    /**
     * Creates the driver command factory and gets the command lists that are in the
     * factory
     * 
     * @param myRobot
     * 
     */
    private void setupDriverCommands(Robot myRobot) {
        
        theDriverCommandFactory = new DriverCommandFactory(myRobot);
        driverCmdList = theDriverCommandFactory.createDriverCommands();
        driverLeftCmdList = theDriverCommandFactory.createDriverLeftCommands(myRobot);
        driverRightCmdList = theDriverCommandFactory.createDriverRightCommands(myRobot);
    }

   
    /**
     * Creates the operator command factory and gets the command list that's created
     * in that factory
     * 
     * @param myRobot
     */
    private void setupOperatorCommands(Robot myRobot) {
        theOperatorCmdFactory = new OperatorCmdFactory();
        theOperatorCmdFactory.init();

        operatorCmdList = theOperatorCmdFactory.createOperatorCommands(myRobot);
    }

   

    /**
     * Cycles through the hashmap, checking if a buttonis pressed. If a button is
     * pressed, grab the command associated with that button and executes it.
     * 
     * @param commandValues
     */
    public void joyStickCommandExecution(HashMap<Integer, Integer[]> commandValues) {
        // keep track of buttons previous iteration
        // compare previous and present buttons
        // if previous was pressed but no longer pressed then stop that certain button
        // if buton is pressed exectue its command
        // go back to step one and repeat 

        Command commandForTheButtonPressed;
        Command operatorCommandsForTheButtonPressed;
        //Command leftDriverCommandsForTheButtonPressed;
        //Command RightDriverCommandsForTheButtonPressed;

        Integer[] buttonPressed;

        //Just get the CommandTankDrive whose key is zero
        commandForTheButtonPressed = driverCmdList.get(0);
        commandForTheButtonPressed.execute();

        // Check for inputs on the operator joystick
        buttonPressed = commandValues.get(CrusaderCommon.OPR_CMD_LIST);
        // if buttons are not null...
        if (previousButtons != null) {
            //if the previous button conains the value of the joystick is in the crusader common operator list...
            if (previousButtons.containsKey(CrusaderCommon.OPR_CMD_LIST)) {
                Integer[] previousButtonsOperator = previousButtons.get(CrusaderCommon.OPR_CMD_LIST);
                //Logger.Log(previousButtonsOperator.toString());
                if (previousButtonsOperator != null && previousButtonsOperator.length > 0) {
                    //makes a list of buttons
                    //List<Integer> buttonPressList = Arrays.asList(buttonPressed);
                    // for each previous button in the previous button operator array...
                    for (int i = 0; i < previousButtonsOperator.length; i++) {
                        if (previousButtonsOperator[i] != null) {

                            int previousButton = previousButtonsOperator[i];
                            if (previousButton != 0) {
                                //if the current button list does not contain previos button...
                                boolean found = false;
                                for (int j = 0; j < buttonPressed.length; j++) {
                                    if (buttonPressed[j] != null && buttonPressed[j] == previousButton) {
                                        found = true;
                                        break;
                                    }
                                }
                                Logger.Log("CommandController.joyStickCommandExecution: PreviousButton = "
                                        + previousButton + " Found: = " + found);

                                if (!found) {
                                    var commandToStop = operatorCmdList.get(previousButton);
                                    commandToStop.stop();
                                }
                            }
                        }
                    }
                }
            }
        }

        boolean buttonIsPressed = false;
        //Logger.Log("CommandController.josystickcommandexecution() : " + buttonPressed.length);
        for (int i = 0; i < buttonPressed.length; i++) {

            if (buttonPressed[i] != null) {
                int index = buttonPressed[i];
                Logger.Log("CommandController.josystickcommandexecution() actual button pressed =  " + index);
                if (index > 0) {

                    buttonIsPressed = true;
                    operatorCommandsForTheButtonPressed = operatorCmdList.get(index);
                    if (operatorCommandsForTheButtonPressed == null) {
                        Logger.Log("CommandController.josystickcommandexecution() no command found");
                    } else {
                        /*
                         * if(buttonPressed[i] == 1 || buttonPressed[i] == 5) { int shootButton =
                         * buttonPressed[i]; operatorCommandsForTheButtonPressed.execute(shootButton); }
                         * else {
                         */
                        if (index == 1 || index == 2 || index == 3 || index == 4 || index == 7 || index == 8
                                || index == 24 || index == 25 || index == 26 || index == 30) {
                            operatorCommandsForTheButtonPressed.execute(index);

                        } else {
                            operatorCommandsForTheButtonPressed.execute(); // why are you null
                        }
                    }

                }

            }
        }
        if (!buttonIsPressed) {
            operatorCommandsForTheButtonPressed = operatorCmdList.get(0);
            operatorCommandsForTheButtonPressed.execute();
        }

        executeDriverCommands(commandValues.get(CrusaderCommon.LEFTDRIVER_CMD_LIST), driverLeftCmdList);
        executeDriverCommands(commandValues.get(CrusaderCommon.RIGHTDRIVER_CMD_LIST), driverRightCmdList);

        previousButtons = new HashMap<Integer, Integer[]>();
        for (Integer joystick : commandValues.keySet()) {
            Integer[] currentButtons = commandValues.get(joystick);
            // Integer[] buttons = currentButtons.clone();//new Integer[currentButtons.length];
            // for (int i = 0; i < currentButtons.length; i++) {
            //     buttons[i] = currentButtons[i];
            // }
            previousButtons.put(joystick, currentButtons.clone());
        }
        //previousButtons = (HashMap<Integer, Integer[]>) commandValues.clone();
    }
    

    private static void executeDriverCommands(Integer[] buttonsPressed, HashMap<Integer, Command> commmands) {       

        boolean buttonIsPressed = false;
        //Logger.Log("CommandController.josystickcommandexecution() : " + buttonPressed.length);
        for (int i = 0; i < buttonsPressed.length; i++) {

            if (buttonsPressed[i] != null) {
                int index = buttonsPressed[i];
                Logger.Log("CommandController.executeDriverCommands() actual button pressed =  " + index);
                if (index > 0) {

                    buttonIsPressed = true;
                    Command command = commmands.get(index);
                    if (command == null) {
                        Logger.Log("CommandController.executeDriverCommands() no command found");
                    } else {
                        command.execute(); // why are you null
                    }
                }
            }
        }
        if (!buttonIsPressed) {
            Command command = commmands.get(0);
            command.execute();
        }
    }

    
     // 
        //if we do need a button then we need to uncomment below and be aware it could effect the driving
        //buttonPressed = commandValues.get(CrusaderCommon.DT_CMD_LIST);

        // boolean driverButtonPressed = false;
        // for (int d = 0; d < buttonPressed.length; d++) {
        //     if (buttonPressed[d] != null) {
        //         int index = buttonPressed[d];
        //         Logger.Log("CommandController.joyStickCommandExecution() - " + index);
        //         if (index > 0) {
                   
        //             commandForTheButtonPressed = driverCmdList.get(index);
        //             if(commandForTheButtonPressed != null){
        //                 commandForTheButtonPressed.execute(); 
        //             }
                    
                   
        //         }

        //     }
        // }
        //if (!driverButtonPressed) {
         //   commandForTheButtonPressed = driverCmdList.get(0);
        //   commandForTheButtonPressed.execute();
        //}

    /**
     * OLD STYLE 2 DRIVER JOYSTICHS
     *HashMap<Integer, Command> driverLeftCmdList;
    HashMap<Integer, Command> driverRightCmdList; 
     * Cycles through the hashmap, checking if a buttonis pressed. If a button is
     * pressed, grab the command associated with that button and executes it.
     * 
     * @param commandValues
     */
    //public void joyStickCommandExecution(HashMap<Integer, Integer[]> commandValues) {
        // Command commandForTheButtonPressed;
        // Command operatorCommandsForTheButtonPressed;
        // // HashMap<Integer, int[]> buttonPressed;
        // Integer[] buttonPressed;

        // // Checks for inputs on the left driver joystick
        // buttonPressed = commandValues.get(CrusaderCommon.INPUT_DRIVER_LEFT_JS);
        // // commandForTheButtonPressed = driverLeftCmdList.get(buttonPressed);

    //     boolean leftDButtonPressed = false;
    //     for (int l = 0; l < buttonPressed.length; l++) {

    //         if (buttonPressed[l] != null) {
    //             int index = buttonPressed[l];
    //             System.out.println(index);
    //             if (index > 0) {
    //                 // if(index >= 0)
    //                 // {
    //                 leftDButtonPressed = true;
    //                 commandForTheButtonPressed = driverLeftCmdList.get(index);
    //                 commandForTheButtonPressed.execute(); // why are you null
    //                 // }
    //                 // }
    //             }

    //             // operatorCommandsForTheButtonPressed = operatorCmdList.get(i);
    //         }
    //     }
    //     if (!leftDButtonPressed) {
    //         commandForTheButtonPressed = driverLeftCmdList.get(0);
    //         commandForTheButtonPressed.execute();
    //     }

    //     // Checks for inputs on the right driver joystick
    //     buttonPressed = commandValues.get(CrusaderCommon.INPUT_DRIVER_RIGHT_JS);
    //     commandForTheButtonPressed = driverRightCmdList.get(buttonPressed);

    //     /*
    //      * if(commandForTheButtonPressed != null) {
    //      * commandForTheButtonPressed.execute(); }
    //      */
    //     boolean rightDButtonPressed = false;
    //     for (int r = 0; r < buttonPressed.length; r++) {

    //         if (buttonPressed[r] != null) {
    //             int index = buttonPressed[r];
    //             System.out.println(index);
    //             if (index > 0) {
    //                 // if(index >= 0)
    //                 // {
    //                 rightDButtonPressed = true;
    //                 commandForTheButtonPressed = driverRightCmdList.get(index);
    //                 commandForTheButtonPressed.execute(); // why are you null
    //                 // }
    //                 // }
    //             }

    //             // operatorCommandsForTheButtonPressed = operatorCmdList.get(i);
    //         }
    //     }
    //     if (!rightDButtonPressed) {
    //         commandForTheButtonPressed = driverRightCmdList.get(0);
    //         commandForTheButtonPressed.execute();
    //     }
    //     // Checks for y inputs on both of the driver joysticks
    //     buttonPressed = commandValues.get(CrusaderCommon.DT_CMD_LIST);

    //     boolean driverButtonPressed = false;
    //     for (int d = 0; d < buttonPressed.length; d++) {
    //         commandForTheButtonPressed = driverCmdList.get(buttonPressed);
    //         if (buttonPressed[d] != null) {
    //             int index = buttonPressed[d];
    //             System.out.println(index);
    //             if (index > 0) {
    //                 // if(index >= 0)
    //                 // {
    //                 // rightDButtonPressed = true;
    //                 commandForTheButtonPressed = driverCmdList.get(buttonPressed);
    //                 commandForTheButtonPressed.execute(); // why are you null
    //                 // }
    //                 // }
    //             }

    //             // operatorCommandsForTheButtonPressed = operatorCmdList.get(i);
    //         }
    //     }
    //     if (!driverButtonPressed) {
    //         commandForTheButtonPressed = driverCmdList.get(0);
    //         commandForTheButtonPressed.execute();
    //     }

    //     // commandForTheButtonPressed = driverCmdList.get(buttonPressed);

    //     // Check for inputs on the operator joystick
    //     buttonPressed = commandValues.get(CrusaderCommon.OPR_CMD_LIST);

    //     boolean buttonIsPressed = false;
    //     // System.out.print(buttonPressed.length);
    //     for (int i = 0; i < buttonPressed.length; i++) {

    //         if (buttonPressed[i] != null) {
    //             int index = buttonPressed[i];
    //             System.out.println(index);
    //             if (index > 0) {
    //                 // if(index >= 0)
    //                 // {
    //                 buttonIsPressed = true;
    //                 operatorCommandsForTheButtonPressed = operatorCmdList.get(index);

    //                 /*
    //                  * if(buttonPressed[i] == 1 || buttonPressed[i] == 5) { int shootButton =
    //                  * buttonPressed[i]; operatorCommandsForTheButtonPressed.execute(shootButton); }
    //                  * else {
    //                  */
    //                 if (index == 1 || index == 2 || index == 4 || index == 7 || index == 8) {
    //                     operatorCommandsForTheButtonPressed.execute(index);
    //                     // System.out.println(operatorCommandsForTheButtonPressed.getClass().getName());

    //                 } else {
    //                     operatorCommandsForTheButtonPressed.execute(); // why are you null
    //                     // System.out.println("execute()");
    //                 }
    //                 // }
    //                 // }
    //             }

    //             // operatorCommandsForTheButtonPressed = operatorCmdList.get(i);
    //         }
    //     }
    //     if (!buttonIsPressed) {
    //         operatorCommandsForTheButtonPressed = operatorCmdList.get(0);
    //         operatorCommandsForTheButtonPressed.execute();
    //     }

    // }
}