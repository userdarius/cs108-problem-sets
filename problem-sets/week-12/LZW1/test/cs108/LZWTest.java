package cs108;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LZWTest {
    public LZWEncoder newEncoder(SortedSet<Character> alphabet, int dictCapacity) {
        throw new Error("A faire : retourner un encodeur concret (partie 1)");
    }

    public LZWDecoder newDecoder(SortedSet<Character> alphabet, int dictCapacity) {
        throw new Error("A faire : retourner un décodeur concret (partie 2)");
    }

    private static SortedSet<Character> stringToSet(String s) {
        SortedSet<Character> set = new TreeSet<>();
        for (int i = 0; i < s.length(); ++i)
            set.add(s.charAt(i));
        return set;
    }

    @Test
    public void encoderCorrectlyEncodesEmptyString() {
        LZWEncoder e = newEncoder(Collections.emptySortedSet(), 10);
        assertTrue(e.encode("").isEmpty());
    }

    @Test
    public void encoderCorrectlyEncodesExample() {
        SortedSet<Character> alphabet = new TreeSet<>(Set.of('a', 'b', 'e', 'n'));
        String str = "bananebabaane";
        LZWEncoder e = newEncoder(alphabet, 8);
        assertEquals(List.of(1, 0, 3, 5, 2, 4, 4, 7), e.encode(str));
    }

    @Test
    public void decoderCorrectlyDecodesEmptyList() {
        LZWDecoder d = newDecoder(Collections.emptySortedSet(), 10);
        assertEquals("", d.decode(List.of()));
    }

    @Test
    public void decoderCorrectlyDecodesExample() {
        SortedSet<Character> alphabet = new TreeSet<>(Set.of('a', 'b', 'e', 'n'));
        LZWDecoder d = newDecoder(alphabet, 8);
        List<Integer> compressed = List.of(1, 0, 3, 5, 2, 4, 4, 7);
        assertEquals("bananebabaane", d.decode(compressed));
    }

    @Test
    public void decoderCorrectlyHandlesTrickyCase() {
        SortedSet<Character> alphabet = new TreeSet<>(Set.of('a'));
        LZWDecoder d = newDecoder(alphabet, 2);
        List<Integer> compressed = List.of(0, 1);
        assertEquals("aaa", d.decode(compressed));
    }

    @Test
    public void repeatedSequenceIsCorrectlyEncodedAndDecoded() {
        SortedSet<Character> alphabet = new TreeSet<>(Set.of('a'));
        int dictCapacity = 128;
        LZWEncoder e = newEncoder(alphabet, dictCapacity);
        LZWDecoder d = newDecoder(alphabet, dictCapacity);
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertEquals(s, d.decode(e.encode(s)));
    }

    @Test
    public void candideIsCorrectlyCompressedAndDecompressed() {
        String candide = "Il y avait en Vestphalie, dans le château de M. le "
                + "baron de Thunder-ten-tronckh, un jeune garçon à qui la "
                + "nature avait donné les moeurs les plus douces. Sa "
                + "physionomie annonçait son âme. Il avait le jugement "
                + "assez droit, avec l'esprit le plus simple; c'est, je "
                + "crois, pour cette raison qu'on le nommait Candide. Les "
                + "anciens domestiques de la maison soupçonnaient qu'il "
                + "était fils de la soeur de monsieur le baron et d'un bon "
                + "et honnête gentilhomme du voisinage, que cette "
                + "demoiselle ne voulut jamais épouser parce qu'il n'avait "
                + "pu prouver que soixante et onze quartiers, et que le "
                + "reste de son arbre généalogique avait été perdu par "
                + "l'injure du temps. Monsieur le baron était un des plus "
                + "puissants seigneurs de la Westphalie, car son château "
                + "avait une porte et des fenêtres. Sa grande salle même "
                + "était ornée d'une tapisserie. Tous les chiens de ses "
                + "basses-cours composaient une meute dans le besoin; ses "
                + "palefreniers étaient ses piqueurs; le vicaire du "
                + "village était son grand-aumônier. Ils l'appelaient tous "
                + "monseigneur, et ils riaient quand il fesait des contes. "
                + "Madame la baronne, qui pesait environ trois cent "
                + "cinquante livres, s'attirait par là une très grande "
                + "considération, et fesait les honneurs de la maison avec "
                + "une dignité qui la rendait encore plus respectable. Sa "
                + "fille Cunégonde, âgée de dix-sept ans, était haute en "
                + "couleur, fraîche, grasse, appétissante. Le fils du "
                + "baron paraissait en tout digne de son père. Le "
                + "précepteur Pangloss était l'oracle de la maison, et le "
                + "petit Candide écoutait ses leçons avec toute la bonne "
                + "foi de son âge et de son caractère. Pangloss enseignait "
                + "la métaphysico-théologo-cosmolonigologie. Il prouvait "
                + "admirablement qu'il n'y a point d'effet sans cause, et "
                + "que, dans ce meilleur des mondes possibles, le château "
                + "de monseigneur le baron était le plus beau des "
                + "châteaux, et madame la meilleure des baronnes "
                + "possibles. Il est démontré, disait-il, que les choses "
                + "ne peuvent être autrement; car tout étant fait pour une "
                + "fin, tout est nécessairement pour la meilleure fin. "
                + "Remarquez bien que les nez ont été faits pour porter "
                + "des lunettes; aussi avons-nous des lunettes. Les jambes "
                + "sont visiblement instituées pour être chaussées, et "
                + "nous avons des chausses. Les pierres ont été formées "
                + "pour être taillées et pour en faire des châteaux; aussi "
                + "monseigneur a un très beau château: le plus grand baron "
                + "de la province doit être le mieux logé; et les cochons "
                + "étant faits pour être mangés, nous mangeons du porc "
                + "toute l'année: par conséquent, ceux qui ont avancé que "
                + "tout est bien ont dit une sottise; il fallait dire que "
                + "tout est au mieux. Candide écoutait attentivement, et "
                + "croyait innocemment; car il trouvait mademoiselle "
                + "Cunégonde extrêmement belle, quoiqu'il ne prît jamais "
                + "la hardiesse de le lui dire. Il concluait qu'après le "
                + "bonheur d'être né baron de Thunder-ten-tronckh, le "
                + "second degré de bonheur était d'être mademoiselle "
                + "Cunégonde; le troisième, de la voir tous les jours; et "
                + "le quatrième, d'entendre maître Pangloss, le plus grand "
                + "philosophe de la province, et par conséquent de toute "
                + "la terre. Un jour Cunégonde, en se promenant auprès du "
                + "château, dans le petit bois qu'on appelait parc, vit "
                + "entre des broussailles le docteur Pangloss qui donnait "
                + "une leçon de physique expérimentale à la femme de "
                + "chambre de sa mère, petite brune très jolie et très "
                + "docile. Comme mademoiselle Cunégonde avait beaucoup de "
                + "disposition pour les sciences, elle observa, sans "
                + "souffler, les expériences réitérées dont elle fut "
                + "témoin; elle vit clairement la raison suffisante du "
                + "docteur, les effets et les causes, et s'en retourna "
                + "tout agitée, toute pensive, toute remplie du désir "
                + "d'être savante, songeant qu'elle pourrait bien être la "
                + "raison suffisante du jeune Candide, qui pouvait aussi "
                + "être la sienne. Elle rencontra Candide en revenant au "
                + "château, et rougit: Candide rougit aussi . Elle lui dit "
                + "bonjour d'une voix entrecoupée; et Candide lui parla "
                + "sans savoir ce qu'il disait. Le lendemain, après le "
                + "dîner, comme on sortait de table, Cunégonde et Candide "
                + "se trouvèrent derrière un paravent; Cunégonde laissa "
                + "tomber son mouchoir, Candide le ramassa; elle lui prit "
                + "innocemment la main; le jeune homme baisa innocemment "
                + "la main de la jeune demoiselle avec une vivacité, une "
                + "sensibilité, une grâce toute particulière; leurs "
                + "bouches se rencontrèrent, leurs yeux s'enflammèrent, "
                + "leurs genoux tremblèrent, leurs mains s'égarèrent. M. "
                + "le baron de Thunder-ten-tronckh passa auprès du "
                + "paravent, et voyant cette cause et cet effet, chassa "
                + "Candide du château à grands coups de pied dans le "
                + "derrière. Cunégonde s'évanouit: elle fut souffletée par "
                + "madame la baronne dès qu'elle fut revenue à elle-même; "
                + "et tout fut consterné dans le plus beau et le plus "
                + "agréable des châteaux possibles.";
        SortedSet<Character> alphabet = stringToSet(candide);
        int dictCapacity = 1024;
        for (int i = 0; i <= candide.length(); i += 5) {
            String s = candide.substring(0, i);
            LZWEncoder e = newEncoder(alphabet, dictCapacity);
            LZWDecoder d = newDecoder(alphabet, dictCapacity);

            assertEquals(s, d.decode(e.encode(s)));
        }
    }
}
