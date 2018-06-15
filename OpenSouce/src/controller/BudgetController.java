package controller;

import java.time.YearMonth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.GoalMoney;

public class BudgetController {
	private LayoutController layoutCon;
	private HomeController homeCon;
	private LoginController loginCon;
	private ObservableList<String> type = FXCollections.observableArrayList("����","�ĺ�","��Ȱ","�Ƿ�","����","��Ÿ");
	public static ObservableList<GoalMoney> monthGoalMoney = FXCollections.observableArrayList();
	public static ObservableList<GoalMoney> yearGoalMoney = FXCollections.observableArrayList();
	@FXML private ComboBox typeCheck;
	@FXML private TextField txtMoney;
	@FXML private Label monthLabel;
	@FXML private Label yearLabel;
	@FXML private TableView<GoalMoney> goalMoneyTable;
	@FXML private TableColumn<GoalMoney, YearMonth> goalMoneyMonthColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyTrafficColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyFoodColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyLifeColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyMedicalColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyPleasureColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyGuitarColumn;
	@FXML private TableColumn<GoalMoney, Integer> goalMoneyTotalColumn;
	private boolean select;
	
	@FXML
	private void previousMonthButton() {
		homeCon.date = homeCon.date.minusMonths(1);
		monthLabel.setText(homeCon.date.getMonthValue()+"��");
		yearLabel.setText(homeCon.date.getYear()+"��");
	}
	@FXML
	private void nextMonthButton() {
		homeCon.date = homeCon.date.plusMonths(1);
		monthLabel.setText(homeCon.date.getMonthValue()+"��");
		yearLabel.setText(homeCon.date.getYear()+"��");
	}
	@FXML
	private void previousYearButton() {
		homeCon.date = homeCon.date.minusYears(1);
		yearLabel.setText(homeCon.date.getYear()+"��");
		yearGoalMoney.clear();
		for(int i=0;i<monthGoalMoney.size();i++) {
	    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
	    		yearGoalMoney.add(monthGoalMoney.get(i));
	    	}
	    }
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}
	@FXML
	private void nextYearButton() {
		homeCon.date = homeCon.date.plusYears(1);
		yearLabel.setText(homeCon.date.getYear()+"��");
		yearGoalMoney.clear();
		for(int i=0;i<monthGoalMoney.size();i++) {
	    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
	    		yearGoalMoney.add(monthGoalMoney.get(i));
	    	}
	    }
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}
	public BudgetController() {
		for(int i=0;i<monthGoalMoney.size();i++) {
			//if(homeCon.date.getYear() == monthGoalMoney.get(i).getDate().get)
		}
	}
	@FXML
	private void initialize() {
		typeCheck.setItems(type);
		typeCheck.setDisable(true);
	    monthLabel.setText(homeCon.date.getMonthValue()+"��");
	    yearLabel.setText(homeCon.date.getYear()+"��");
	    for(int i=0;i<monthGoalMoney.size();i++) {
	    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
	    		yearGoalMoney.add(monthGoalMoney.get(i));
	    	}
	    }
	    goalMoneyMonthColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
	    goalMoneyTrafficColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	    goalMoneyFoodColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	    goalMoneyLifeColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	    goalMoneyMedicalColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	    goalMoneyPleasureColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());;
	    goalMoneyGuitarColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	    goalMoneyTotalColumn.setCellValueFactory(cellData -> cellData.getValue().getTrafficProperty().asObject());
	}
	@FXML
	private void setTotal() {
		typeCheck.setDisable(true);
		select=false;
	}
	@FXML
	private void setType() {
		typeCheck.setDisable(false);
		select=true;
	}
	@FXML
	private void addButton() {
		int dateCheck =0;
		if(select == false) {
			int overType=0;
			for(int i=0;i<monthGoalMoney.size();i++) {
			if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
				if(Integer.parseInt(txtMoney.getText())<(monthGoalMoney.get(i).getTraffic()+monthGoalMoney.get(i).getFood()+monthGoalMoney.get(i).getLife()+monthGoalMoney.get(i).getMedical()+monthGoalMoney.get(i).getPleasure()+monthGoalMoney.get(i).getGuitar())) {
					dateCheck=1;
					overType=1;
					break;
				}
				monthGoalMoney.get(i).setTotal(Integer.parseInt(txtMoney.getText()));
				dateCheck=1;
			}
			}
			if(dateCheck==0) {
				monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,0,0,Integer.parseInt(txtMoney.getText())));
			}
			if(overType==1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("�� �� ��ǥ�ݾ� ���� ����");
				alert.setHeaderText("�� �� ��ǥ�ݾ��� �� ������ ��ǥ�ݾ��Ѿ׺��� �۽��ϴ�.");
				alert.setContentText("�ٽ� �Է����ּ���.");
				alert.show();
			}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���� ����");
			alert.setHeaderText("�� �� �����ǥ�ݾ��� �����Ǿ����ϴ�.");
			alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" �� �����ǥ�ݾ�: "+monthGoalMoney.get(0).getTotal());
			alert.show();
			}
		}else {
			if(typeCheck.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� ����");
				alert.setHeaderText("������ ���õ��� �ʾҽ��ϴ�.");
				alert.setContentText("������ �������ּ���.");
				alert.show();
			}else {
				int overTotal=0;
				if(typeCheck.getValue() == "����") {
			for(int i=0;i<monthGoalMoney.size();i++) {
				if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
					if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
						dateCheck=1;
						overTotal=1;
						break;
					}
					monthGoalMoney.get(i).setTraffic(Integer.parseInt(txtMoney.getText()));
					dateCheck=1;
				}
			}
			if(dateCheck==0) {
				monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),Integer.parseInt(txtMoney.getText()),0,0,0,0,0,Integer.parseInt(txtMoney.getText())));
			}
			if(overTotal ==1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� �����ǥ�� ���� ����");
				alert.setHeaderText("�� ���� �����ǥ���� �� �� ��i�ݾ׺��� �����ϴ�.");
				alert.setContentText("�ٽ� �Է����ּ���.");
				alert.show();
			}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���� ����");
			alert.setHeaderText("���� ��ǥ�ݾ��� �����Ǿ����ϴ�.");
			alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" ���� ��ǥ�ݾ�: "+monthGoalMoney.get(0).getTraffic());
			alert.show();
			        }
				}else {
					if(typeCheck.getValue() == "�ĺ�") {
						for(int i=0;i<monthGoalMoney.size();i++) {
							if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
								if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
									dateCheck=1;
									overTotal=1;
									break;
								}
								monthGoalMoney.get(i).setFood(Integer.parseInt(txtMoney.getText()));
								dateCheck=1;
							}
						}
						if(dateCheck==0) {
							monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,Integer.parseInt(txtMoney.getText()),0,0,0,0,Integer.parseInt(txtMoney.getText())));
						}
						if(overTotal ==1) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("�ĺ� �����ǥ�� ���� ����");
							alert.setHeaderText("�� �ĺ� �����ǥ���� �� �� ��i�ݾ׺��� �����ϴ�.");
							alert.setContentText("�ٽ� �Է����ּ���.");
							alert.show();
						}else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("���� ����");
						alert.setHeaderText("�ĺ� ��ǥ�ݾ��� �����Ǿ����ϴ�.");
						alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" �ĺ� ��ǥ�ݾ�: "+monthGoalMoney.get(0).getFood());
						alert.show();
						        }
					}else {
						if(typeCheck.getValue() == "��Ȱ") {
							for(int i=0;i<monthGoalMoney.size();i++) {
								if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
									if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
										dateCheck=1;
										overTotal=1;
										break;
									}
									monthGoalMoney.get(i).setLife(Integer.parseInt(txtMoney.getText()));
									dateCheck=1;
								}
							}
							if(dateCheck==0) {
								monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,Integer.parseInt(txtMoney.getText()),0,0,0,Integer.parseInt(txtMoney.getText())));
							}
							if(overTotal ==1) {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("��Ȱ �����ǥ�� ���� ����");
								alert.setHeaderText("�� ��Ȱ �����ǥ���� �� �� ��i�ݾ׺��� �����ϴ�.");
								alert.setContentText("�ٽ� �Է����ּ���.");
								alert.show();
							}else {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("���� ����");
							alert.setHeaderText("��Ȱ ��ǥ�ݾ��� �����Ǿ����ϴ�.");
							alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" �ĺ� ��ǥ�ݾ�: "+monthGoalMoney.get(0).getLife());
							alert.show();
							        }
						}else {
							if(typeCheck.getValue() == "�Ƿ�") {
								for(int i=0;i<monthGoalMoney.size();i++) {
									if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
										if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
											dateCheck=1;
											overTotal=1;
											break;
										}
										monthGoalMoney.get(i).setMedical(Integer.parseInt(txtMoney.getText()));
										dateCheck=1;
									}
								}
								if(dateCheck==0) {
									monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,Integer.parseInt(txtMoney.getText()),0,0,Integer.parseInt(txtMoney.getText())));
								}
								if(overTotal ==1) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("�Ƿ� �����ǥ�� ���� ����");
									alert.setHeaderText("�� �Ƿ� �����ǥ���� �� �� ��i�ݾ׺��� �����ϴ�.");
									alert.setContentText("�ٽ� �Է����ּ���.");
									alert.show();
								}else {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("���� ����");
								alert.setHeaderText("�Ƿ� ��ǥ�ݾ��� �����Ǿ����ϴ�.");
								alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" �Ƿ� ��ǥ�ݾ�: "+monthGoalMoney.get(0).getMedical());
								alert.show();
								        }
							}else {
								if(typeCheck.getValue() == "����") {
									for(int i=0;i<monthGoalMoney.size();i++) {
										if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
											if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
												dateCheck=1;
												overTotal=1;
												break;
											}
											monthGoalMoney.get(i).setPleasure(Integer.parseInt(txtMoney.getText()));
											dateCheck=1;
										}
									}
									if(dateCheck==0) {
										monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,Integer.parseInt(txtMoney.getText()),0,Integer.parseInt(txtMoney.getText())));
									}
									if(overTotal ==1) {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("���� �����ǥ�� ���� ����");
										alert.setHeaderText("�� ���� �����ǥ���� �� �� ��i�ݾ׺��� �����ϴ�.");
										alert.setContentText("�ٽ� �Է����ּ���.");
										alert.show();
									}else {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("���� ����");
									alert.setHeaderText("���� ��ǥ�ݾ��� �����Ǿ����ϴ�.");
									alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" �ĺ� ��ǥ�ݾ�: "+monthGoalMoney.get(0).getPleasure());
									alert.show();
									        }
								}else {
									for(int i=0;i<monthGoalMoney.size();i++) {
										if(monthGoalMoney.get(i).getDate().equals(YearMonth.from(homeCon.date.getMonth()))) {
											if(Integer.parseInt(txtMoney.getText())>(monthGoalMoney.get(i).getTotal())) {
												dateCheck=1;
												overTotal=1;
												break;
											}
											monthGoalMoney.get(i).setGuitar(Integer.parseInt(txtMoney.getText()));
											dateCheck=1;
										}
									}
									if(dateCheck==0) {
										monthGoalMoney.add(new GoalMoney(loginCon.users.get(loginCon.userNumber).getID(),YearMonth.from(homeCon.date),0,0,0,0,0,Integer.parseInt(txtMoney.getText()),Integer.parseInt(txtMoney.getText())));
									}
									if(overTotal ==1) {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("��Ÿ �����ǥ�� ���� ����");
										alert.setHeaderText("�� ��Ÿ �����ǥ���� �� �� ��i�ݾ׺��� �����ϴ�.");
										alert.setContentText("�ٽ� �Է����ּ���.");
										alert.show();
									}else {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("���� ����");
									alert.setHeaderText("��Ÿ ��ǥ�ݾ��� �����Ǿ����ϴ�.");
									alert.setContentText(homeCon.date.getYear()+"�� "+homeCon.date.getMonthValue()+"��"+" �ĺ� ��ǥ�ݾ�: "+monthGoalMoney.get(0).getGuitar());
									alert.show();
									        }
								}
							}
						}
					}
				}
			}
		}
		 for(int i=0;i<monthGoalMoney.size();i++) {
		    	if(monthGoalMoney.get(i).getDate().getYear() == homeCon.date.getYear()) {
		    		yearGoalMoney.add(monthGoalMoney.get(i));
		    	}
		    }
		 goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
			goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
			goalMoneyMonthColumn.setSortable(true);
			goalMoneyTable.sort();
	}
	private ObservableList<GoalMoney> getYearGoalMoney(){
		return yearGoalMoney;
	}
	public void setBudget(LayoutController layoutCon) {
		this.layoutCon = layoutCon;
		//if(!monthGoalMoney.isEmpty())
		goalMoneyTable.setItems(getYearGoalMoney());
		goalMoneyMonthColumn.setSortType(TableColumn.SortType.ASCENDING);
		goalMoneyTable.getSortOrder().add(goalMoneyMonthColumn);
		goalMoneyMonthColumn.setSortable(true);
		goalMoneyTable.sort();
	}

}