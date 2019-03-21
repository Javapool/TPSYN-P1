
public class Cavalier extends Piece
 {
    public Cavalier (String nom, String couleur)
    {
        super(nom,couleur);
    }

    public  boolean estValide (Position depart, Position arrivee)
    {
        boolean valide=false;
        if (arrivee.getLigne()>=0 && arrivee.getColonne()>=0 && arrivee.getLigne()<=7 && arrivee.getColonne()<=7 ){
            if (norme(depart,arrivee)==5){
                valide=true;
            }
        }
        return valide;
    };

};
