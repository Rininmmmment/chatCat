package chatbot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class PostAuto {
	// choice_cat_and_create: 板ごとの猫の選び方を反映させて投稿する
	public static void choice_cat_and_create(String bbs_type, String user_account) {
		SelectPromptAndPost postai = new SelectPromptAndPost();
		List<String> homeCatList = Arrays.asList("Chef", "Doctor", "Pen", "Rocker", "Rounin", "OeoeSake");

		switch(bbs_type){
        case "home":
	        // ランダムで猫を選ぶ
			Random rand = new Random();
		    int num = rand.nextInt(homeCatList.size());
		    System.out.println(num);
		    String cat = homeCatList.get(num);
		    // 投稿する
		    postai.create(bbs_type, user_account, cat);
            break;
        case "matatabi":
        	postai.create(bbs_type, user_account, "OeoeSake");
            break;
        case "restaurant":
        	postai.create(bbs_type, user_account, "Chef");
            break;
        case "school":
        	postai.create(bbs_type, user_account, "Rounin");
            break;
        case "clinic":
        	postai.create(bbs_type, user_account, "Doctor");
            break;
		}
	}
	
	// timer_create指定時間後にchoice_cat_and_create
	public void timer_create(String bbs_type, String user_account) {
		
		// 一定時間後に複数回実行する場合
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
			int cnt=0;
 
			@Override
			public void run() {
				long t = System.currentTimeMillis();
				choice_cat_and_create(bbs_type, user_account);
				cnt++;
				//5回実行で停止
				if ( cnt <= 1 ) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if ( cnt >= 2 ) {
					timer.cancel();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 5000);
	}
}
 