
public class MySListener extends PluginListener{
	private MySalary MyS;
	private MySActions MySA;
	private MySData MySD;
	
	public MySListener(MySalary MyS){
		this.MyS = MyS;
	}
	
	public void Initialize(){
		MySA = MyS.MySA;
		MySD = MyS.MySD;
	}
	
	public boolean onCommand(Player player, String[] cmd){
		if((cmd[0].equalsIgnoreCase("/MySalary"))||(cmd[0].equalsIgnoreCase("/MyS"))){
			if(cmd.length == 1){
				player.sendMessage("�a-----�fMySalary �a-�f Version: "+MyS.version+"�a-----");
				if(!MyS.isLatest() && player.isAdmin()){
					player.sendMessage("�a-----�fThe is an update! �a-�f Version: "+MyS.currver+"�a-----");
				}
				if(MySD.GPay){
			        String group = (player.getGroups() != null ? player.getGroups()[0] : etc.getDataSource().getDefaultGroup().Name);
					if(MySD.GroupPay.containsKey(group)){
						double pay = MySD.GroupPay.get(group);
						if(MySD.dCo){
							player.sendMessage("�aYour Salary is: �e"+priceForm(pay)+" "+MySD.getMoneyName());
						}
						else{
							player.sendMessage("�aYour Salary is: �e"+((int)pay)+" "+MySD.getMoneyName());
						}
					}
					else{
						player.sendMessage("�cYou are considered unemployed...");
					}
				}
				else{
					if(MySD.dCo){
						player.sendMessage("�aYour Salary is: �e"+priceForm(MySD.regpay)+" "+MySD.getMoneyName());
					}
					else{
						player.sendMessage("�aYour Salary is: �e"+((int)MySD.regpay)+" "+MySD.getMoneyName());
					}
				}
				if(MySD.Claim){
					player.sendMessage("�a/mys claim -�e Claims pay check if avalible");
				}
				player.sendMessage("�a/mys time -�e Displays time till next pay");
				if(player.isAdmin()){
					player.sendMessage("�a/mys broadcast -�e Broadcasts time till next pay");
					player.sendMessage("�a/mys pay -�e Immediately pays all players");
				}
			}
			else if(cmd[1].equalsIgnoreCase("time")){
				return MySA.displayTime(player);
			}
			else if(cmd[1].equalsIgnoreCase("claim")){
				if(MySD.Claim){
					return MySA.ClaimCheck(player);
				}
				else{
					player.sendMessage("[�aMySalary�f]�c Your checks are auto-deposited!");
				}		
			}
			else if(cmd[1].equalsIgnoreCase("broadcast")){
				if(player.isAdmin()){
					return MySA.broadcastTime();
				}
				else{
					player.sendMessage("[�aMySalary�f]�c You do not have permission to use that!");
				}
			}
			else if(cmd[1].equalsIgnoreCase("pay")){
				if(player.isAdmin()){
					return MySA.manualPay();
				}
				else{
					player.sendMessage("[�aMySalary�f]�c You do not have permission to use that!");
				}
			}
			else{
				player.sendMessage("[�aMySalary�f]�c UNKNOWN MySalary COMMAND!");
			}
			return true;
		}
		else if(cmd[0].equalsIgnoreCase("/#save-all") || cmd[0].equalsIgnoreCase("/#stop")){
			if(player.isOp()){
				MySD.dumpData();
			}
		}
		return false;
	}
	
	public void onLogin(Player player){
	    String name = player.getName();
	    String group = (player.getGroups() != null ? player.getGroups()[0] : etc.getDataSource().getDefaultGroup().Name);
		if(!MySD.W2s.containsKey(name)){
			MySD.W2s.put(name, false);
		}
		
		if(!MySD.Employer.containsKey(name)){
			MySD.Employer.put(name, group);
		}
		else if(!MySD.Employer.get(name).equals(group)){
			MySD.Employer.put(name, group);
		}
	}
	
	public boolean onConsoleCommand(String[] cmd){
		if(cmd[0].equalsIgnoreCase("mysalary") || cmd[0].equalsIgnoreCase("mys")){
			if(cmd.length == 1){
				System.out.println("-----MySalary - Version: "+MyS.version+"-----");
				System.out.println("mys time - Displays time till next pay");
				System.out.println("mys broadcast - Broadcasts time till next pay");
				System.out.println("mys pay - Immediately pays all players");
				return true;
			}
			else{
				if(cmd[1].equalsIgnoreCase("time")){
					return MySA.consoleDisplayTime();
				}
				else if(cmd[1].equalsIgnoreCase("pay")){
					return MySA.manualPay();
				}
				else if(cmd[1].equalsIgnoreCase("broadcast")){
					return MySA.broadcastTime();
				}
			}
		}
		else if(cmd[0].equalsIgnoreCase("save-all") || cmd[0].equalsIgnoreCase("stop")){
			MySD.dumpData();
		}
		return false;
	}
	
	private String priceForm(double price){
		String newprice = String.valueOf(price);
		String[] form = newprice.split("\\.");
		if(form[1].length() == 1){
			newprice += "0";
		}
		else{
			newprice = form[0] + "." + form[1].substring(0, 2);
		}
		return newprice;
	}
}

/*******************************************************************************\
* MySalary                                                                       *
* Copyright (C) 2011-2012 Visual Illusions Entertainment                        *
* @author darkdiplomat <darkdiplomat@visualillusionsent.net>                    *
*                                                                               *
* This file is part of MySalary.                                                 *                       
*                                                                               *
* This program is free software: you can redistribute it and/or modify          *
* it under the terms of the GNU General Public License as published by          *
* the Free Software Foundation, either version 3 of the License, or             *
* (at your option) any later version.                                           *
*                                                                               *
* This program is distributed in the hope that it will be useful,               *
* but WITHOUT ANY WARRANTY; without even the implied warranty of                *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *
* GNU General Public License for more details.                                  *
*                                                                               *
* You should have received a copy of the GNU General Public License             *
* along with this program.  If not, see http://www.gnu.org/licenses/gpl.html.   *
\*******************************************************************************/
