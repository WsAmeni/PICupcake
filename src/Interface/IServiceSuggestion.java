/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author jihed
 */
public interface IServiceSuggestion <S> {
    public void insertSuggestionP(S s);
    public void insertSuggestionProd(S s);
    public void insertSuggestionB(S s);
    public void deleteSuggestionP(int idce, int idcr , int idp);
    public void deleteSuggestionProd(int idce, int idcr , int idprod);
    public void deleteSuggestionB(int idce, int idcr , int idb);
}
