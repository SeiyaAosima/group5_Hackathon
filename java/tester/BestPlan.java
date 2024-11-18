package tester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;

/**
 * SemNetAppクラス
 */
public class BestPlan {
    public static void main(String[] args) {
        // H2データベースの初期化
        //Database.initializeDatabase();

        // Thymeleafのテンプレート設定
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");

        // TemplateEngineの設定
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // JavalinにThymeleafを登録
        JavalinRenderer.register(new JavalinThymeleaf(templateEngine), ".html");

        // Javalinアプリの作成
        Javalin app = Javalin.create().start(7000);

        // デフォルトの設定 (http://localhost:7000/best_planの初期設定)
        app.get("/best_plan", ctx -> {
            Map<String, Object> model = new HashMap<>();
            /*
            String queryStr = ctx.queryParam("queryStr");
            SemanticNet sn = new SemanticNet();   // セマンティックネットを構築
            if (sn.isEmpty()) {
                sn.addInitialLinks();
            }

            model.put("sn", sn);   // セマンティックネットを追加
            if (queryStr != null) {
                ArrayList<Link> query = strToQuery(queryStr);
                String result = sn.query(query);
                model.put("result", result);
            } else {
                queryStr = "?x is-a ?s\n?x lives-in ?y\n?y is-in-the ?z";   // queryStrのデフォルト値を変更
            }
            model.put("query", queryStr);   // クエリを追加
            */
            //ctx.render("/main_page.html", model);   // main_page.htmlへ保存
            ctx.render("/task_input.html", model);   // task_input.htmlへ保存
        });

        // 「タスク追加」ボタンが押された場合
        app.post("/best_plan", ctx -> {
            Map<String, Object> model = new HashMap<>();
            /*
            String queryStr = ctx.formParam("queryStr");
            SemanticNet sn = new SemanticNet();   // セマンティックネットを構築

            if (sn.isEmpty()) {
                sn.addInitialLinks();
            }

            model.put("sn", sn);   // セマンティックネットを追加

            if (queryStr != null) {
                ArrayList<Link> query = strToQuery(queryStr);
                String result = sn.query(query);   // マッチングする
                model.put("result", result);   // 結果を追加
            }
            */
            String task_name = ctx.formParam("task_name");
            model.put("task_name", task_name);   // タスク名を追加

            int limit_month = Integer.parseInt(ctx.formParam("limit_month"));
            model.put("limit_month", limit_month);   // 期限(月)を追加

            int limit_day = Integer.parseInt(ctx.formParam("limit_day"));
            model.put("limit_day", limit_day);   // 期限(日)を追加

            int limit_hour = Integer.parseInt(ctx.formParam("limit_hour"));
            model.put("limit_hour", limit_hour);   // 期限(時)を追加

            int limit_minute = Integer.parseInt(ctx.formParam("limit_minute"));
            model.put("limit_minute", limit_minute);   // 期限(分)を追加

            int time_hour = Integer.parseInt(ctx.formParam("time_hour"));
            model.put("time_hour", time_hour);   // かかる時間(時)を追加

            int time_minute = Integer.parseInt(ctx.formParam("time_minute"));
            model.put("time_minute", time_minute);   // かかる時間(分)を追加

            ctx.render("/task_input.html", model);   // task_input.htmlへ保存
            ctx.render("/main_page.html", model);   // main_page.htmlへ保存
        });
    }

    /*
    private static ArrayList<Link> strToQuery(String queryStr) {
        ArrayList<Link> query = new ArrayList<>();
        if (queryStr != null) {
            String[] lines = queryStr.split("\n");   // 改行で区切って配列に入れる (配列の要素は1つの文)
            for (String line : lines) {
                line = line.trim();   // 文全体の前後の空白を削除
                String[] tokens = line.split("\\s+");   // 空白で区切って配列に入れる (配列の要素は1つの語)
                if (tokens.length == 3) {
                    // 語の数が3であれば、query(ArrayList<Link>型)の要素として各語を追加
                    query.add(new Link(tokens[1], tokens[0], tokens[2], new SemanticNet()));
                }
            }
        }
        return query;
    }
    */
}


