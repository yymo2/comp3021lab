package lab9;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LettersUI extends Application {

	final static int SCENE_WIDTH = 300;
	final static int SCENE_HEIGHT = 150;

	public static void main(String[] args) {
		launch(LettersUI.class, args);
	}

	@Override
	public void start(Stage stage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10); // Gap between nodes

		String[] string_thread = { "A", "B", "C", "D", "E"};
		for (String s : string_thread) {
			Text text = new Text(s);
			text.setFont(new Font(50));
			text.setVisible(false);
			hbox.getChildren().addAll(text);
		}
		
		Scene scene = new Scene(hbox, SCENE_WIDTH, SCENE_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Example");
		stage.show();

		// Since now you have to add 3 more Threads, Can we use a for loop instead ?
		for(int i = 0; i < string_thread.length; i++) {
			Thread t = new Thread(new MyTask((Text) hbox.getChildren().get(i), this));
			t.setDaemon(true);
			t.start();
		}
		
	}

	public synchronized void showText(Text text, boolean show) {
		// the parameter show tells if the text has to appear o disappear
		//text.setVisible(show);
		
		if(!show) {
			text.setVisible(show);
			notify();
		}
		
		if(show) {
			if(text.isVisible()) {
				try {
					wait();
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			text.setVisible(show);

        }


	}
	class MyTask implements Runnable {

		int timeBudgetms = 5 * 60 * 1000;
		LettersUI instance;
		Text text;

		public MyTask(Text text, LettersUI zeroOROne) {
			this.text = text;
			this.instance = zeroOROne;
		}

		@Override
		public void run() {
			boolean show = true;
			long startTime = System.currentTimeMillis();
			while ((startTime + timeBudgetms) > System.currentTimeMillis()) {
				instance.showText(text, show);
				show = !show;
				try {
					Random rn = new Random();
					int range = 3000 - 1000 + 1;
					int randomNum = rn.nextInt(range) + 1000;
					Thread.yield();
					Thread.sleep(randomNum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

