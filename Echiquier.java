
//package projetSynthese;

public class Echiquier implements MethodesEchiquier {
private static final int HAUT_BOARD=8;
private static final int LARG_BOARD=8;

private Case location [][]=new Case[HAUT_BOARD][LARG_BOARD];

  public Echiquier()
  {
    for (int i = 0; i < HAUT_BOARD; i++)
    {
      for (int j = 0; i < LARG_BOARD; i++)
      {
        location[i][j]=new Case();
      }
    }
  }

//M�thode � compl�ter
public void debuter()
{
int k;
  for (k=0; k < 8; k++)
  {
	location[1][k].ajouterPiece(new Pion("p" + String.valueOf(k+1), "noir"));
	location[6][k].ajouterPiece(new Pion("p" + String.valueOf(k+1), "blanc"));
//Autres pions
  }
  location[0][0].ajouterPiece(new Tour        ("t1", "noir"));
  location[0][7].ajouterPiece(new Tour        ("t2", "noir"));
  location[0][1].ajouterPiece(new Cavalier    ("c1", "noir"));
  location[0][6].ajouterPiece(new Cavalier    ("c2", "noir"));
  location[0][2].ajouterPiece(new Fou         ("f1", "noir"));
  location[0][5].ajouterPiece(new Fou         ("f2", "noir"));
  location[0][3].ajouterPiece(new Roi         ("k",  "noir"));
  location[0][4].ajouterPiece(new Reine       ("q",  "noir"));

//pièces noires

  location[7][0].ajouterPiece(new Tour      ("p1", "blanc"));
  location[7][7].ajouterPiece(new Tour      ("p1", "blanc"));
  location[7][1].ajouterPiece(new Cavalier  ("c1", "blanc"));
  location[7][6].ajouterPiece(new Cavalier  ("c1", "blanc"));
  location[7][2].ajouterPiece(new Fou       ("f1", "blanc"));
  location[7][5].ajouterPiece(new Fou       ("f1", "blanc"));
  location[7][3].ajouterPiece(new Roi       ("k",  "blanc"));
  location[7][4].ajouterPiece(new Reine     ("r",  "blanc"));


}

public Case getCase ( int ligne, int colonne )
{
return location[ligne][colonne];
}

//M�thode � compl�ter
public boolean captureParUnPionPossible ( Position depart,Position arrivee )
  {
	  return true;
  }

//M�thode � compl�ter
public boolean cheminPossible ( Position  depart , Position arrivee)
{

	if(!getCase(depart.x,depart.y).getPiece().estValidE(depart,arrivee))
	{
		return false;
	}
	if(type(getCase(depart.x,depart.y).getPiece())==Pion)
	{

	}
	if(getcase(arrivee.x,arrivee.y).estOccupee())
	{
		return false;
	}

	if(type(getcase(depart.x,depart.y).getPiece())!=Cavalier)
	{
		//abs() retourne la valeur absolue d'un nombre(voir définition à la fin d'échiquier)
		int Vecteurx=(arrivee.x-depart.x);
		int Vecteury=(arrivee.y-depart.y);
		//signe x ou y prend une valeur dans -1,0,1 pour déterminer la direction du mouvement en x et en y(0 étant nul)
		int Signex;
		int Signey;

		//Valeurmax détermine l'ampleur du mouvement (en x ou en y. ça ne fait aucune différence)
		int valeurMax=(abs(Vecteurx) > abs(Vecteury)) ? Vecteurx : Vecteury;
		//assigne un signe à x et y
		if(Vecteurx!=0)
		{Signex=Vecteurx/abs(Vecteurx);}
		else
		{Signex=0;}

		if(Vecteury!=0)
		{Signey=Vecteury/abs(Vecteury);}
		else
		{Signey=0;}

		//valide chaque case entre départ et arrivée pour voir si le chemin est bloqué
		for (int i = 1; i < valeurMax; i++) {
			if(getCase(depart.x+(i*Signex), depart.y+(i*Signey)).getPiece()!=null)
			{
				return false;
			}
		}
	}
	return true;
}
/*
public boolean roquePossible (Position depart, Position arrivee )
{
}

public boolean priseEnPassantPossible( Position depart, Position arrivee )
{
}

public boolean promotionPossible (Position depart, Position arrivee)
  {
  if ( location [depart.getLigne()][depart.getColonne()].getPiece().getCouleur() == "blanc" ){}
  }
  */
public static void main ( String [] args )
  {
  Echiquier e = new Echiquier ();
  e.debuter();
  afficher(e);

  }

public static void afficher(Echiquier board)
{
	System.out.println();
	for (int i = 0; i < HAUT_BOARD; i++) {
		for (int j = 0; j < LARG_BOARD; j++) {
			if(board.getCase(i,j).getPiece()!=null)
			{
				System.out.printf("%5s",board.getCase(i,j).getPiece().getNom());
			}
			else
			{
				System.out.printf("%5s","");
			}
		}
		System.out.println("\n");
	}

}

public static int abs(int valeur)
	{
		int absolu = (valeur < 0) ? -valeur : valeur;
		return absolu;
	}

}
