package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * <h1>COMP2130 Assignment 2</h1>
 * 
 * @author Emmanuel Sogelola - 101203022
 * @author Stefan Maric - 101208175
 * @author Kevin Sabas - 101049251
 *
 */
public class Main extends Application {
	private static MemberManager ChessClub;

	public static void main(String[] args) {
		launch(args);

	}

	public static void showError(String err) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(err);

		alert.showAndWait();
	}

	public static void showMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Member Information");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	public static void updateTable(TableView<Member> table) {
		table.getItems().clear();

		for (int i = 0; i < ChessClub.getNumMembers(); i++) {

			table.getItems().add(ChessClub.allMembers()[i]);
		}
	}

	@Override
	public void init() {
		ChessClub = new MemberManager(10);

		ChessClub.addMember("Emmanuel", "Sogelola", 10, 5);
		ChessClub.addMember("James", "Crow", 10, 5);
		ChessClub.addMember("Conrad", "Ren", 100, 5);
		ChessClub.addMember("All", "Mig", 1, 1);
		ChessClub.addMember("Jimmy", "Low", 30, 5);
		ChessClub.addMember("C", "plus plus", 90, 65);

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			final int WINDOW_WIDTH = 600;
			final int WINDOW_HEIGHT = 600;

			ObservableList<Member> data = FXCollections.observableArrayList(ChessClub.allMembers());

			ImageView ivLogo = new ImageView(new Image("file:images\\chess_club_logo.png"));

			TableView<Member> table = new TableView<Member>();

			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			table.setEditable(false);

			TableColumn<Member, Integer> idCol = new TableColumn<Member, Integer>("ID");
			idCol.setMinWidth(50);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Member, String> firstNameCol = new TableColumn<Member, String>("First Name");

			firstNameCol.setMinWidth(100);
			firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

			TableColumn<Member, String> lastNameCol = new TableColumn<Member, String>("Last Name");
			lastNameCol.setMinWidth(100);
			lastNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));

			TableColumn<Member, Integer> gamesCol = new TableColumn<Member, Integer>("Games");
			gamesCol.setMinWidth(50);
			gamesCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numGamesPlayed"));

			TableColumn<Member, Integer> winCol = new TableColumn<Member, Integer>("Wins");
			winCol.setMinWidth(50);
			winCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numWins"));

			TableColumn<Member, Integer> lossCol = new TableColumn<Member, Integer>("Losses");
			lossCol.setMinWidth(50);
			lossCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numLosses"));

			TableColumn<Member, String> winrateCol = new TableColumn<Member, String>("WINRATE %");
			winrateCol.setMinWidth(60);
			winrateCol.setCellValueFactory(new PropertyValueFactory<Member, String>("winPercentage"));

			updateTable(table);

			table.getColumns().addAll(idCol, firstNameCol, lastNameCol, gamesCol, winCol, lossCol, winrateCol);

			Button btnUp = new Button("\u2191");
			btnUp.setPrefSize(45, 30);
			btnUp.setFocusTraversable(false);
			btnUp.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (table.getSelectionModel().getSelectedIndex() < 0) {
						table.getSelectionModel().clearAndSelect(0);
					} else {
						int index = table.getSelectionModel().getSelectedIndex();

						if (table.getItems().size() > 2 && index != 0 && index != -1) {

							table.getSelectionModel().select(index - 1);

						}
					}

				}
			});

			Button btnDown = new Button("\u2193");
			btnDown.setPrefSize(45, 30);
			btnDown.setFocusTraversable(false);
			btnDown.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (table.getSelectionModel().getSelectedIndex() < 0) {
						table.getSelectionModel().clearAndSelect(0);
					} else {
						int index = table.getSelectionModel().getSelectedIndex();

						if (index < table.getItems().size() - 1 && index != -1) {

							table.getSelectionModel().select(index + 1);

						}
					}
				}
			});
			Button btnAdd = new Button("ADD");
			btnAdd.setPrefSize(100, 30);
			btnAdd.setFocusTraversable(false);
			btnAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					Dialog<String> dialog = new Dialog<>();
					dialog.setTitle("Add a Member");
					dialog.setHeaderText("Add a Member");
					DialogPane dialogPane = dialog.getDialogPane();
					Label lblFirstName = new Label("FIRST NAME");
					TextField txtFirstName = new TextField("");
					Label lblLastName = new Label("Last NAME");
					TextField txtLastName = new TextField("");
					Label lblNumGamesPlayed = new Label("Games Played");
					TextField txtNumGamesPlayed = new TextField("");
					Label lblNumWins = new Label("Wins");
					TextField txtNumWins = new TextField("");

					dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
					dialogPane.setContent(new VBox(8, lblFirstName, txtFirstName, lblLastName, txtLastName,
							lblNumGamesPlayed, txtNumGamesPlayed, lblNumWins, txtNumWins));

					dialog.setResultConverter((ButtonType button) -> {
						if (button == ButtonType.OK) {
							int gamesPlayed = 0, numWins = 0;
							try {
								gamesPlayed = Integer.parseInt(txtNumGamesPlayed.getText());
								numWins = Integer.parseInt(txtNumWins.getText());

								if (numWins > gamesPlayed) {
									showError("Members number of wins cannot exceed the number of games played!");
									btnAdd.fire();

								} else {
									return txtFirstName.getText() + "," + txtLastName.getText() + ","
											+ txtNumGamesPlayed.getText() + "," + txtNumWins.getText();
								}
							} catch (NumberFormatException e1) {
								showError("INVALID INPUT!\nMake sure you entered a valid games played/number of wins!");
								btnAdd.fire();
							}

						}
						return null;
					});
					Optional<String> optionalResult = dialog.showAndWait();
					optionalResult.ifPresent((String results) -> {
						String[] data = results.split(",");

						ChessClub.addMember(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
						table.getItems().add(ChessClub.allMembers()[ChessClub.getNumMembers() - 1]);

					});
				}

			});
			Button btnEdit = new Button("EDIT");
			btnEdit.setPrefSize(100, 30);
			btnEdit.setFocusTraversable(false);
			btnEdit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					if (table.getSelectionModel().getSelectedIndex() >= 0) {
						Member toEdit = table.getSelectionModel().getSelectedItem();

						Dialog<String> dialog = new Dialog<>();
						dialog.setTitle("Edit a Member");
						dialog.setHeaderText("Edit a Member");
						DialogPane dialogPane = dialog.getDialogPane();
						Label lblFirstName = new Label("FIRST NAME");
						TextField txtFirstName = new TextField(toEdit.getFirstName());
						Label lblLastName = new Label("Last NAME");
						TextField txtLastName = new TextField(toEdit.getLastName());
						Label lblNumGamesPlayed = new Label("Games Played");
						TextField txtNumGamesPlayed = new TextField(toEdit.getNumGamesPlayed() + "");
						Label lblNumWins = new Label("Wins");
						TextField txtNumWins = new TextField(toEdit.getNumWins() + "");

						dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
						dialogPane.setContent(new VBox(8, lblFirstName, txtFirstName, lblLastName, txtLastName,
								lblNumGamesPlayed, txtNumGamesPlayed, lblNumWins, txtNumWins));

						dialog.setResultConverter((ButtonType button) -> {
							if (button == ButtonType.OK) {
								int gamesPlayed = 0, numWins = 0;
								try {
									gamesPlayed = Integer.parseInt(txtNumGamesPlayed.getText());
									numWins = Integer.parseInt(txtNumWins.getText());

									if (numWins > gamesPlayed) {
										showError("Members number of wins cannot exceed the number of games played!");
										btnEdit.fire();

									} else {
										return txtFirstName.getText() + "," + txtLastName.getText() + ","
												+ txtNumGamesPlayed.getText() + "," + txtNumWins.getText();
									}
								} catch (NumberFormatException e1) {
									showError(
											"INVALID INPUT!\nMake sure you entered a valid games played/number of wins!");
									btnEdit.fire();
								}

							}
							return null;
						});
						Optional<String> optionalResult = dialog.showAndWait();
						optionalResult.ifPresent((String results) -> {
							String[] data = results.split(",");

							ChessClub.setFirstName(toEdit.getId(), data[0]);
							ChessClub.setLastName(toEdit.getId(), data[1]);
							ChessClub.setGamesPlayed(toEdit.getId(), Integer.parseInt(data[2]));
							ChessClub.setNumWins(toEdit.getId(), Integer.parseInt(data[3]));
							ChessClub.updateLosses(toEdit.getId());
							updateTable(table);

						});
					} else {
						showError("NO MEMBER TO EDIT! Please select/add one!");

					}
				}

			});

			Button btnRemove = new Button("REMOVE");
			btnRemove.setPrefSize(100, 30);
			btnRemove.setFocusTraversable(false);
			btnRemove.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					if (table.getSelectionModel().getSelectedIndex() >= 0) {
						Member toDelete = table.getSelectionModel().getSelectedItem();
						ChessClub.deleteMember(toDelete.getId());
						table.getItems().remove(table.getSelectionModel().getSelectedIndex());

					} else {
						showError("NO MEMBER TO DELETE!\nMake sure you have added a member/selected one.");

					}
				}

			});
			Button btnBest = new Button("Best Player");
			btnBest.setPrefSize(100, 30);
			btnBest.setFocusTraversable(false);
			btnBest.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent ae) {

					showMessage(ChessClub.bestMembers());
				}

			});

			Button btnMostWins = new Button("MOST WINS");
			btnMostWins.setPrefSize(100, 30);
			btnMostWins.setFocusTraversable(false);
			btnMostWins.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent ae) {

					showMessage(ChessClub.mostWins());
				}

			});

			FlowPane root = new FlowPane();
			root.setPadding(new Insets(20, 10, 20, 10));
			root.setVgap(15);
			root.setHgap(10);
			root.setAlignment(Pos.TOP_CENTER);

			HBox hboOptions = new HBox();
			hboOptions.getChildren().addAll(btnUp, btnAdd, btnEdit, btnRemove, btnDown);
			HBox hboOthers = new HBox();
			hboOthers.getChildren().add(btnBest);
			hboOthers.getChildren().add(btnMostWins);

			root.getChildren().addAll(ivLogo, table, hboOptions, hboOthers);
			root.setAlignment(Pos.BOTTOM_CENTER);

			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

			primaryStage.setTitle("Chess Club Manager");
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(ivLogo.getImage());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
