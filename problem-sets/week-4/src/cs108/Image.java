package cs108;

/**
 * Une image continue et infinie, représentée par une fonction associant une
 * valeur d'un type donné (p.ex. une couleur) à chaque point du plan.
 */

public interface Image<T> {
    T apply(double x, double y);
}
