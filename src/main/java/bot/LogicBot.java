package bot;

import common.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LogicBot {
    private final Random random = new Random();
    private final String help = "Вот, что я могу:\n" +
//            "- /joke - рассказать анекдот\n" +
            "- /getid - получить ID чата\n" +
//            "- /weather - прогноз погоды\n" +
//            "- /gamecity - игра в Города\n" +
            "- /talk - просто поболтать с ботом\n" +
            "- /stop - остановить бота" +
            "- /help - помощь";
    private final HashMap<String, ArrayList<Tuple<String, String>>> dialog = new HashMap<>();
    public String userName = "";
    private String dialogPosition = "";
    private final String[] jokes = new String[]{
            "Hi",
            "Bye",
            "Chao"
    };

    public LogicBot(){
        ArrayList<Tuple<String, String>> ar1 = new ArrayList<>();
        ar1.add(new Tuple<>("/help", this.help));
        ar1.add(new Tuple<>("/getid", "Получить ID чата"));
//        ar1.add(new Tuple<>("/weather", "Функция временно недоступна"));
//        ar1.add(new Tuple<>("/joke", "Анекдот"));
//        ar1.add(new Tuple<>("/gamecity", "Функция временно недоступна"));
        ar1.add(new Tuple<>("/talk", "Функция временно недоступна"));
        ar1.add(new Tuple<>("/stop", "Остановить бота"));
        this.dialog.put(this.help, ar1);
        ArrayList<Tuple<String, String>> ar2 = new ArrayList<>();
        ar2.add(new Tuple<>("да", "Анекдот"));
        ar2.add(new Tuple<>("нет", this.help));
        this.dialog.put("Хотите ещё анекдот?", ar2);
    }

    public String getAnswer(String enter, String chatId) throws Exception {
//        if (enter.equalsIgnoreCase(""))
//            return "empty";
//        if (dialogPosition.equals("")){
//            this.userName = enter;
//            this.dialogPosition = this.help;
//        }
//        for (Tuple<String, String> tuple: this.dialog.get(this.dialogPosition)){
//            if (tuple.getKey().equals(enter))
//                this.dialogPosition = tuple.getValue();
//        }
//        if (this.dialogPosition.equals("Анекдот")){
//            this.dialogPosition = "Хотите ещё анекдот?";
//            return jokes[random.nextInt(jokes.length)] + "\n" + this.dialogPosition;
//        }
//        if (this.dialogPosition.equals("Функция временно недоступна")){
//            this.dialogPosition = this.help;
//            return "Функция временно недоступна" + "\n" + this.dialogPosition;
//        }
//        if (this.dialogPosition.equals("/stop")) throw new Exception("Бот остановлен");
//        return this.dialogPosition;
        return "Your telegramID: " + chatId;
    }
}