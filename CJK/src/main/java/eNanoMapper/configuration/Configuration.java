package eNanoMapper.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JKChang
 * @date 15-Nov-2016
 * @Description: 
 * 
 * This helper class reads Slimmer configuration files that specify which part
 * of the ontology is to be kept or removed. The configuration file uses a
 * line-based instruction format. Each line specifies part of the ontology to be
 * kept or removed. An example is:
 * <add>
 * +D(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch +D:http://www.bioassayontology.org/bao#BAO_0000697 add branch
 * +U(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch
 * +U(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch
 * +(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add single class
 * +(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add single class
 * </add>
 *
 * *<remove>
 * -D(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch
 * -:(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add single class
 * </remove>
 *
 */

public class Configuration {

}
