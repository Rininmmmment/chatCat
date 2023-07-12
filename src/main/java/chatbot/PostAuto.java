package chatbot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class PostAuto {
	// choice_cat_and_create: 板ごとの猫の選び方を反映させて投稿する
	public static void choice_cat_and_create(String bbs_type, String user_account) {
		PostAiMutter postai = new PostAiMutter();
		List<String> catList = Arrays.asList("Chef", "Doctor", "Pen", "Rocker", "Rounin", "OeoeSake");

		switch(bbs_type){
        case "home":
	        // ランダムで猫を選ぶ
			Random rand = new Random();
		    int num = rand.nextInt(catList.size());
		    System.out.println(num);
		    String cat = catList.get(num);
		    // 投稿する
		    postai.create(bbs_type, user_account, cat, "none");
            break;
        case "matatabi":
        	postai.create(bbs_type, user_account, "OeoeSake", "matatabi");
            break;
        case "restaurant":
        	postai.create(bbs_type, user_account, "Chef", "restaurant");
            break;
        case "school":
        	postai.create(bbs_type, user_account, "Rounin", "school");
            break;
        case "clinic":
        	postai.create(bbs_type, user_account, "Doctor", "clinic");
            break;
		}
	}
	
	// timer_create指定時間後にchoice_cat_and_create
	public void timer_create(String bbs_type, String user_account) {
		// 一定時間ごに1回だけ実行する場合
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Timer timer = new Timer(false);
//		TimerTask task = new TimerTask() {
// 
//			@Override
//			public void run() {
//				choice_cat_and_create(bbs_type, user_account);
//				timer.cancel();
//			}
//		};
//		timer.schedule(task, 1000);
		
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
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if ( cnt >= 2 ) {
					timer.cancel();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 10000);
	}
}
 