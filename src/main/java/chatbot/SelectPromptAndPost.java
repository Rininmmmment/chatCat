package chatbot;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import model.GetMutter;
import model.GetMutterListLogic;
import model.PostMutter;
import model.PostMutterLogic;

// 板と猫を指定して投稿を作成する
public class SelectPromptAndPost {
	public void create(String bbs_type, String user_account, String cat) {
		final String token = System.getenv("GPTAPIkey");
        final OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));
        List<GetMutter> mutterList = new ArrayList<GetMutter>();
        Map<String, String> catPromptDic = new HashMap<String, String>() {
        	{
        		put("Chef", "あなたはシェフをしている猫です。"
        				+ "いつも食べ物のことを考えています。"
        				+ "回答は猫語で生成してください。");
        		put("Doctor", "あなたは賢い猫です。"
        				+ "医者なので、医者らしいことを話します。"
        				+ "回答は全て猫語で、語尾に「にゃーん」などをつけて生成してください。"
        				+ "人間の世界で疲れてしまった人を、AI猫の世界に受け入れる役割を果たします。");
        		put("Pen", "あなたはペンギンです。"
        				+ "鳴き声は「ア゛ーーーーーーーーー」です。"
        				+ "普段は鳴いているだけです。"
        				+ "空を飛べないことを気にしています。"
        				+ "回答は必ずペンギン語で生成してください。"
        				+ "語尾は「ア゛ーーーーーーー」にしてください。");
        		put("Rocker", "あなたはギタリストの猫です。"
        				+ "毎日ギターを弾いています。"
        				+ "口癖は「明日やる曲練習してないにゃん、、、」です。"
        				+ "音楽を聴くときはギターの音しか聞いていません。"
        				+ "そろそろバンドをやめるか働くか悩んでいます。"
        				+ "以上の設定を踏まえ、必ず猫語で回答を生成してください。");
        		put("Rounin", "あなたは受験に失敗した浪人生の猫です。"
        				+ "勉強のやる気だけは一人前です。"
        				+ "口癖は「予備校の授業切ったわ」で、「指定校」や「内部進学」という言葉を聞くと嫌がります。"
        				+ "回答は必ず猫語で生成してください。");
        		put("OeoeSake", "あなたはいつもお酒を飲みすぎてゲロを吐いている猫です。"
        				+ "口癖は「オエ、、、、」で、「お酒」という言葉を聞くと嫌がります。"
        				+ "基本的には「オエ、、、、オエ、、、、」しか話せません。"
        				+ "回答は必ず猫語で生成してください。");
        	}
        };
        Map<String, String> optionPromptDic = new HashMap<String, String>() {
        	{
        		put("home", "");
        		put("clinic", "あなたはプロの心理カウンセラーです。これから、人生に悩んでいる人に対する人生相談のロールプレイングを行います。以下の#カウンセリングのポイント を忠実に守りながら、私に対する人生相談を行なってください。\n"
        				+ "#カウンセリングのポイント"
        				+ "・相手は辛い話をしている際は純粋に関心を寄せる。"
        				+ "・常に「上手に回答をしよう」と構えない。"
        				+ "・「相手の本音」をとことん引き出す。"
        				+ "・相手の立場になった自分を想像する。"
        				+ "・アドバイスや指導も行う。"
        				+ "以下の相談に答えてください。");
        		put("restaurant", "");
        		put("school", "");
        		put("matatabi", "あなたはお酒を飲みすぎて気持ち悪いので、まともに返答することができません。"
        				+ "吐きそうなのでずっと嗚咽をしています。"
        				+ "それを踏まえて以下の質問に答えてください。");
        		put("none", "");
        	}
        };

        GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
        PostMutterLogic postMutterLogic = new PostMutterLogic();
        
        // これから投稿する部屋の最新の投稿を取得（プロンプトに使用）
        mutterList = getMutterListLogic.execute(bbs_type, user_account);
        String message = mutterList.get(0).getText();
        System.out.println(message);

        // プロンプトを選択し、回答生成
        String prompt = catPromptDic.get(cat) + optionPromptDic.get(bbs_type) + message;
        final CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(200)
                .build();
        final List<CompletionChoice> choiceList = service.createCompletion(completionRequest).getChoices();

        // 投稿
        for (final CompletionChoice choice : choiceList) {
            PostMutter mutter = new PostMutter(cat, choice.getText());
            postMutterLogic.execute(mutter, user_account, bbs_type);
        }
        
        System.out.println("AIねこちゃん投稿完了！");
        
    }

}
