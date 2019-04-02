//package com.projetSynthese;

public class Echiquier implements MethodesEchiquier {
private static final int HAUT_BOARD=8;
private static final int LARG_BOARD=8;


private Case location [][]=new Case[HAUT_BOARD][LARG_BOARD];

public Echiquier()
  {
    for (int i = 0; i < HAUT_BOARD; i++)
    {
      for (int j = 0; j < LARG_BOARD; j++)
      {
        this.location[i][j]=new Case();
      }
    }
  }

//M�thode � compl�ter
public void debuter()
{

  for (int k=0; k < 8; k++)
  {
	this.location[1][k].ajouterPiece(new Pion("p" + String.valueOf(k+1), "noir"));
	this.location[6][k].ajouterPiece(new Pion("p" + String.valueOf(k+1), "blanc"));
//Autres pions
  }
  this.location[0][0].ajouterPiece(new Tour        ("t1", "blanc"));
  this.location[0][7].ajouterPiece(new Tour        ("t2", "blanc"));
  this.location[0][1].ajouterPiece(new Cavalier    ("c1", "blanc"));
	this.location[0][6].ajouterPiece(new Cavalier    ("c2", "blanc"));
  this.location[0][2].ajouterPiece(new Fou         ("f1", "blanc"));
  this.location[0][5].ajouterPiece(new Fou         ("f2", "blanc"));
  this.location[0][3].ajouterPiece(new Roi         ("r",  "blanc"));
  this.location[0][4].ajouterPiece(new Reine       ("d",  "blanc"));

//pièces noires

	this.location[7][0].ajouterPiece(new Tour      ("t1", "noir"));
	this.location[7][7].ajouterPiece(new Tour      ("t1", "noir"));
	this.location[7][1].ajouterPiece(new Cavalier  ("c1", "noir"));
	this.location[7][6].ajouterPiece(new Cavalier  ("c1", "noir"));
	this.location[7][2].ajouterPiece(new Fou       ("f1", "noir"));
	this.location[7][5].ajouterPiece(new Fou       ("f1", "noir"));
	this.location[7][3].ajouterPiece(new Roi       ("r",  "noir"));
	this.location[7][4].ajouterPiece(new Reine     ("d",  "noir"));


}

public Case getCase ( int ligne, int colonne )
{
return this.location[ligne][colonne];
}

//M�thode � compl�ter
public boolean captureParUnPionPossible ( Position depart,Position arrivee )
  {
		Piece pieceChoisie=this.getCase(depart.getColonne(),depart.getLigne()).getPiece();

				if (this.getCase(arrivee.getColonne(),arrivee.getLigne()).getPiece()!=null&&
				this.getCase(arrivee.getColonne(),arrivee.getLigne()).getPiece().getCouleur()!=pieceChoisie.getCouleur()){
						return true;
				}

	  return false;
  }

//M�thode � compl�ter
public boolean cheminPossible ( Position  depart , Position arrivee)
{
	if(depart==arrivee){
		return true;
	}
	Piece pieceChoisie=this.getCase(depart.getColonne(),depart.getLigne()).getPiece();
	if(!pieceChoisie.estValide(depart,arrivee))
	{
		return false;
	}

	if(this.getCase(arrivee.getColonne(),arrivee.getLigne()).estOccupee())
	{
		return false;
	}

	if (pieceChoisie.getNom().charAt(0)=='p'&&pieceChoisie.norme(depart,arrivee)==2){
		return captureParUnPionPossible(depart, arrivee);
	}
	else if(pieceChoisie.getNom().charAt(0)=='p')
	{
			if(this.getCase(arrivee.getColonne(),arrivee.getLigne()).getPiece()!=null)
		{
			return false;
		}
	}
	else if(pieceChoisie.getNom().charAt(0)!='c')
	{
		//abs() retourne la valeur absolue d'un nombre(voir définition à la fin d'échiquier)
		int Vecteurx=(arrivee.getColonne()-depart.getColonne());
		int Vecteury=(arrivee.getLigne()-depart.getLigne());
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
			if(this.getCase(depart.getColonne()+(i*Signex), depart.getLigne()+(i*Signey)).getPiece()!=null)
			{
				return false;
			}
		}
	}
	return true;
}

public void afficher()
{
	System.out.println();
	for (int i = 0; i < HAUT_BOARD; i++) {
		for (int j = 0; j < LARG_BOARD; j++) {
			if(this.getCase(i,j).getPiece()!=null)
			{
				System.out.printf("%5s",this.getCase(i,j).getPiece().getNom());
			}
			else
			{
				System.out.printf("%5s"," ");
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
