
public class Fou extends Piece
 {
    public Fou (String nom, String couleur)
    {
        super(nom,couleur);
    }

    public  boolean estValide (Position depart, Position arrivee)
    {
        boolean valide=false;
        if (arrivee.getLigne()>=0 && arrivee.getColonne()>=0 && arrivee.getLigne()<=7 && arrivee.getColonne()<=7 ){
            for(int i=1;i<8;i++)
            if (norme(depart,arrivee)==2*Math.pow(i,2)){
                valide=true;
            }
        }
        return valide;
    };

};