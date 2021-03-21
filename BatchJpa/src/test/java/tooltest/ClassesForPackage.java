package tooltest;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassesForPackage {
    /**
     * parcourt le package afin de trouver les class
     *
     * @param packageName        le package
     * @param restrictionPackage converve uniquement les classes contenues dans le package en parametre
     * @return un stream class
     * @throws ClassNotFoundException classe non trouvée
     */
    public static Stream< Class > getListClass(String packageName, boolean restrictionPackage) throws ClassNotFoundException {
        Reflections reflections = new Reflections(packageName);
        Collection< String > stringList = reflections.getStore().get(SubTypesScanner.class.getSimpleName()).values();
        return getClassStream(packageName, restrictionPackage, stringList);
    }

    public static Stream< Class > getAllEntities(String packageName, boolean restrictionPackage) throws ClassNotFoundException {
        //Reflections reflections = new Reflections(packageName);
        //List< String > stringList = reflections.getTypesAnnotatedWith(Entity.class).stream().map(Class::getName).collect(Collectors.toList());
        //return getClassStream(packageName, restrictionPackage, stringList);
        return getClassStream(packageName,
                restrictionPackage,
                getListClassNameWithAnnotation(packageName, Entity.class));
    }

    /**
     * @param packageName
     * @param annotation
     * @return
     */
    private static List< String > getListClassNameWithAnnotation(String packageName, Class< ? extends Annotation > annotation) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(annotation).stream().map(Class::getName).collect(Collectors.toList());
    }

    /**
     * @param packageName
     * @param restrictionPackage
     * @param stringList
     * @return
     * @throws ClassNotFoundException
     */
    private static Stream< Class > getClassStream(String packageName, boolean restrictionPackage, Collection< String > stringList) throws ClassNotFoundException {
        List< Class > myTypes = new ArrayList<>();
        for (String s : stringList) {
            Class< ? > clazz = Class.forName(s);
            if (restrictionPackage) {
                //si le nom du package ne contient pas celui en paramètre on stocke pas
                if (clazz.getPackage().getName().contains(packageName)) {
                    myTypes.add(clazz);
                }
            } else {
                myTypes.add(clazz);
            }
        }
        if (CollectionUtils.isNotEmpty(myTypes)) {
            return myTypes.stream().sorted(Comparator.comparing(Class::getSimpleName));
        }
        return Stream.empty();
    }

    public static Stream< Class > getAllDataAnnotation(String packageName, boolean restrictionPackage) throws ClassNotFoundException {
        return getClassStream(packageName,
                restrictionPackage,
                getListClassNameWithAnnotation(packageName, Data.class));
    }
}
