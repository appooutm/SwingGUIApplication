import javax.swing.*;
import java.awt.*;
import java.util.Random;

//mostentire de la player la obiect
public class Player extends Obiect  {
    Random r = new Random();
    Handler handler;
    public static int CUR_COL = 0;
    public static int MAX_COL = 55;

    public Player(int x, int y, All_players all_players,Handler handler)
    {
        super(x,y,all_players);
        this.handler = handler;
//        corX = 1;
//        corX = r.nextInt(5) * 1;
//        corY = r.nextInt(5);
    }
    public Rectangle coliziune()
    {
        return new Rectangle(x,y,32,32);
    }
    //polimorfism de mostenire
    @Override
    public void tick()
    {
        x += corX;
        y += corY;
        x = Game.border_game(x,0,Game.WIDTH - 37);
        y = Game.border_game(y,0,Game.HEIGHT - 60);
        col();

    }
    public Game.STATE col()
    {
        //INAMIC PRINCIPAL ACUM ESTE tmpObiect
        for(int i = 0; i< handler.object.size(); i++)
        {
            Obiect tmpObiect = handler.object.get(i);
            if (tmpObiect.getId() == All_players.Enemy || tmpObiect.getId() == All_players.Static_enemy)
            {

                if(coliziune().intersects(tmpObiect.coliziune()))
                {
                    //codul pentru a evita coliziunea obiectelor
                    Life_design.LIFE -=2;
                    CUR_COL++;
                    if ( CUR_COL == MAX_COL) {
                        // pop up ca jocul a fost finisat
                        Object[] options = {"Return","Exit"};
                        int response = JOptionPane.showOptionDialog(
                                null,
                                "You lost the game with score 55 points!", "You lost!",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.DEFAULT_OPTION,
                                null,
                                options, options[1]);
                        if(response == 0){
                            return Game.STATE.Menu;
                        }else if(response == 1){
                            System.exit(1);
                        }else{
                            System.out.println("Nu a fost aleasa optiunea");
                        }
                    }
                }
            }
        }
        return Game.STATE.Menu;
    }
    @Override
    public void render(Graphics g)
    {

        g.setColor(Color.BLACK);
        g.fillRect(x, y, 16, 16);
        g.drawRect(x - 5, y - 5,26,26);
        g.drawRect(x - 10, y - 10,36,36);
        g.drawString("Nr de coliziuni curente =" + CUR_COL , 10, 540);
    }


}