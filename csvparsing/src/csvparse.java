import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class csvparse {
    public static void processCSV(String file) {

        String csvFile = file;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        String openDiv = "<div class=\"entry\">\n<div class=\"container\">\n<div class=\"MiniAlbum\">\n<table cellspacing=\"5\">\n<tbody>";

        //end of left side
        String closeDiv = "</tbody>\n</table>\n</div>";

        String openList = "<div class=\"Alb-Description\">\n<div class=\"tableContainer1\">\n<div class=\"tableContainer2\">\n<table class=\"myTable\">\n<tbody>\n<tr>\n<th>Trk</th>\n<th>Title</th>\n<th>Composer</th>\n</tr>";

        try {
            //left side
            br = new BufferedReader(new FileReader(csvFile));
            System.out.println(openDiv);
            line = br.readLine();
            String[] leftCol = line.split(cvsSplitBy);
            String albumName = leftCol[3].substring(1,leftCol[3].length()-1);
            String date = leftCol[0].substring(1,leftCol[0].length()-1);
            String id = leftCol[1].substring(1,leftCol[1].length()-1);
            String genre = leftCol[2].substring(1,leftCol[2].length()-1);
            String aName = "<tr>\n<th colspan=\"2\" class=\"albumName\">\n<b>"+albumName+"</b>\n</th>\n</tr>\n<tr>\n<th colspan=\"2\" style=\"text-align:center;\">\n<img src=\"img/"+id+".jpg\" width=\"400\" height=\"400\">\n</th>\n</tr>\n";
            String aDate = "<tr>\n<th scope=\"row\">Release Date</th>\n<td>\n<p>"+date+"</p>\n</td>\n</tr>\n<tr>\n";
            String aId = "<th scope=\"row\">Catalog No.</th>\n<td>\n<p>"+id+"</p>\n</td>\n</tr>\n";
            String aGen = "<tr>\n<th scope=\"row\">Genre</th>\n<td>\n<p>"+genre+"</p>\n</td>\n</tr>\n";
            String aBuy = "<tr>\n<th colspan=\"2\" class=\"albumName\">\n<b>Buy it here:</b><br>\n</th>\n</tr>\n<tr>\n<th colspan=\"2\" style=\"text-align:center;\">" +
                    "\n<a href=\"\" target=\"_blank\"><img src=\"img/akiba_hobby_icon.png\"></a><br>" +
                    "\n<a href=\"\" target=\"_blank\"><img src=\"img/tora.gif\"></a>\n</th>\n</tr>";
            System.out.println(aName + aDate + aId + aGen + aBuy);
            System.out.println(closeDiv);

            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dolor sed viverra ipsum nunc aliquet bibendum enim. In massa tempor nec feugiat. Nunc aliquet bibendum enim facilisis gravida. Nisl nunc mi ipsum faucibus vitae aliquet nec ullamcorper. Amet luctus venenatis lectus magna fringilla. Volutpat maecenas volutpat blandit aliquam etiam erat velit scelerisque in. Egestas egestas fringilla phasellus faucibus scelerisque eleifend. Sagittis orci a scelerisque purus semper eget duis. Nulla pharetra diam sit amet nisl suscipit. Sed adipiscing diam donec adipiscing tristique risus nec feugiat in. Fusce ut placerat orci nulla. Pharetra vel turpis nunc eget lorem dolor. Tristique senectus et netus et malesuada.";

            String endList = "<tr>\n<th colspan=\"3\" style=\"text-align:center;\">\n<audio controls>\n<source src=\"xfds/"+id+".mp3\" type=\"audio/mpeg\">\n</audio>\n</th>\n</tr>\n</tbody>\n</table>\n</div>\n</div>\n<div class=\"comment\">\n<div class=\"popup\" onclick=\"myFunction()\">Maintainer's short take:\n<span class=\"popuptext\" id=\"myPopup\">"+loremIpsum+"</span>\n</div>\n</div>\n</div>\n</div>\n</div>\n"+
                    "<div class=\"entrySpacing\"></div>\n";


            //right side
            System.out.println(openList);
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] col = line.split(cvsSplitBy);
                //format of csv
                //date,ID,genre,name,trackNr,trackName,composer
                String trackNr = col[4].substring(1,col[4].length()-1);
                String trackName = col[5].substring(1,col[5].length()-1);
                String composer = col[6].substring(1,col[6].length()-1);

                System.out.println("<tr>\n<td>"+trackNr+"</td>\n<td>"+trackName+"</td>\n<td>"+composer+"</td>\n</tr>");
            }
            System.out.println(endList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * current keys in db
         *-> ROCK
         *-> METAL
         *-> METALCORE
         *-> DEATH METAL
         * ANCO undead corporation
         *-> INSTRUMENTAL
         * DECD demetori
         *-> VOCAL
         *-> POP
         *-> TRANCE
         */
        /**
         * CHANGE KEY HERE
         */

        //String key = "ANCD";
        //String key = "ANCO";
        //String key = "BFCD";
        //String key = "CLST";
        //String key = "DDBY";
        //String key = "DECD";
        //String key = "DRCD";
        //String key = "DVSP";
        //String key = "MTCD";
        String key = "RDWL";
        //String key = "VDFD";
        //String key = "XITH";
        //String key = "YTCD";
        //String key = "YVCD";

        File[] files = new File("../dbs/"+key).listFiles();
        Arrays.sort(files);
        for (File file : files) {
            String path = "../dbs/"+key+"/"+file.getName();
            processCSV(path);
        }
    }
}
