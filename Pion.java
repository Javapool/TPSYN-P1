/**
 * Pion
 */
public class Pion extends Piece
 {
    boolean positionDepart=true;
    public Pion (String nom, String couleur)
    {
        super(nom,couleur);
    }

    public  boolean estValide (Position depart, Position arrivee){
        boolean valide=false;
        if(bonSens(depart,arrivee)){
            //valide que le mouvement est fait a lintérieur de l'échiquier
            if (arrivee.getLigne()>=0 && arrivee.getColonne()>=0 && arrivee.getLigne()<=7 && arrivee.getColonne()<=7 ){
                //valide que le mouvement est de 1 case en x ou y ou les deux.
                if (norme(depart,arrivee)==1 || norme(depart,arrivee)==2){
                    valide=true;
                    this.positionDepart=false;
                }
                //verifie si c'est le premier mouvement du pion, pour lui permettre de bouger de 2 cases
                else if(this.positionDepart==true){
                    if (norme(depart,arrivee)==4){
                        valide=true;
                        this.positionDepart=false;
                    }
                }
            }

        }
        return valide;
    }

    public  boolean bonSens (Position depart, Position arrivee){
        boolean valide=false;
        //valide si le pion noir descend ou le pion blanc monte
        if(this.getCouleur()=="blanc"){
            if(arrivee.getLigne()-depart.getLigne()>0){
                valide=true;
            }
        }
        else{
            if(arrivee.getLigne()-depart.getLigne()<0){
                valide=true;
            }
        }
        return valide;
    }
}