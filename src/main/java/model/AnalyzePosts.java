package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.MuttersDAO;

public class AnalyzePosts {
	// 各部屋について投稿を取得し、累計投稿数を計算するメソッド
	public static Map<String, Integer> getPostedTimes(String user_account) {
		List<GetMutter> allMutterList = new ArrayList<GetMutter>();
		List<String> BbsList = Arrays.asList("home", "matatabi", "restaurant", "school", "clinic");
		Map<String, Integer> PostCountsEachBbsList = new HashMap<String, Integer>();
		MuttersDAO mutters_dao = new MuttersDAO();
		for(int i = 0; i < 5; i++){
			String bbs_type = BbsList.get(i);
			allMutterList = mutters_dao.findAll(user_account, bbs_type+"_MUTTERS");  // 各部屋の全投稿のリスト
			
			// 各部屋の全投稿の中から、投稿者がログインユーザーと一致する投稿だけ抽出し、mutterPostedByUserListに格納
			List<GetMutter> mutterPostedByUserList = allMutterList.stream().filter(mutter -> user_account.equals(mutter.getUserid()))
					.collect(Collectors.toList());
			
			// 投稿数を数えて辞書に格納
			PostCountsEachBbsList.put(bbs_type, mutterPostedByUserList.size());
		}
		return PostCountsEachBbsList;
	}
	
	// getPostedTimesの総和
	public int sumPostedTimes(String user_account) {
		Map<String, Integer> PostCountsEachBbsList = getPostedTimes(user_account);
		List<Integer> PostedTimesList = new ArrayList<Integer>(PostCountsEachBbsList.values()); 
		int postedTimesSum = PostedTimesList.stream().mapToInt( l -> l ).sum();
		return postedTimesSum;
	}
	
	// sumPostedTimesの値からレベルを判定する
	public int calculateLevel(String user_account) {
		int postedTimes = sumPostedTimes(user_account);
		int level = 0;
		if(postedTimes <= 10) {
			level = 1;
		}else if(postedTimes <= 50) {
			level = 2;
		}else if(postedTimes <= 100) {
			level = 3;
		}else if(postedTimes <= 200) {
			level = 4;
		}else if(postedTimes <= 400) {
			level = 5;
		}
		return level;
	}
}
