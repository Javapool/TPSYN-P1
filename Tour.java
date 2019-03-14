public class Tour {
    public Tour (String nom, String couleur,int ligne,int colonne)
    {
        super(nom,couleur);
        Position pos=new Position(ligne,colonne);
    }

    public  boolean estValide (Position depart, Position arrivee)
    {
        boolean valide=false;
        if (arrivee.getLigne()>=0 && arrivee.getColonne()>=0 && arrivee.getLigne()<=7 && arrivee.getColonne()<=7 ){
            for (int i = 1; i < 8; i++) {
                if (norme(depart,arrivee)==pow(i,2)){
                    valide=true;
                }
            }
        }
        return valide;
    };
    
};