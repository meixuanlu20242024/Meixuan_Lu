package com.insurance.system.shared.domain.service;

import com.insurance.system.shared.domain.payload.SanctionAmlCheckRequest;
import com.insurance.system.shared.domain.payload.SanctionIndividual;
import de.uni_jena.cs.fusion.similarity.jarowinkler.JaroWinklerSimilarity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import com.insurance.system.shared.usermanagement.payload.ApiResponse;

import javax.xml.parsers.*;
import java.net.URL;
import java.util.*;


@Slf4j
@Service
public class SanctionsService  implements ISanctionsService{
    @Override
    public ResponseEntity<?> checkSanctionsbyName(SanctionAmlCheckRequest request) {
        try {

            List<URL> urls = new ArrayList<>() ;
            urls.add(new URL("https://scsanctions.un.org/resources/xml/en/consolidated.xml")) ;
//            urls.add(new URL("https://scsanctions.un.org/hph62en-dprk.xml")) ;


            for ( URL url: urls
            ) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(url.
                        openStream());



                String firstNameToSearch = request.getFirstName();
                String lastNameToSearch = request.getLastName();
                String documentNumberToSearch = request.getReferenceNumber();

                List<SanctionIndividual> foundIndividuals = searchForName(doc, firstNameToSearch , lastNameToSearch);
//                SanctionIndividual foundDocumentIndividual = searchForDocumentNumber(doc, documentNumberToSearch);

                if(!foundIndividuals.isEmpty() ){
                        return ResponseEntity.ok().body(foundIndividuals);
                }
//                else {
//                    if (foundDocumentIndividual != null) {
//
//                        return ResponseEntity.ok().body(foundDocumentIndividual);
//                    } else {
//                        System.out.println("The document number '" + documentNumberToSearch + "' was not found in the XML.");
//                        return ResponseEntity.ok().body("The document number '" + documentNumberToSearch + "' was not found in the XML.");
//                    }
//                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body( new ApiResponse(true , "Client not found om sanctions list"));
    }




    private List<SanctionIndividual> searchForName(Document doc, String firstNameToSearch, String lastNameToSearch) {
        NodeList individuals = doc.getElementsByTagName("INDIVIDUALS");
        List<SanctionIndividual>  resultIndividuals = new ArrayList<>() ;

        for (int i = 0; i < individuals.getLength(); i++) {
            Element individual = (Element) individuals.item(i);
            NodeList documentElements = individual.getElementsByTagName("INDIVIDUAL");

            for (int j = 0; j < documentElements.getLength(); j++) {
                Element document = (Element) documentElements.item(j);

                // Check if the INDIVIDUAL_DOCUMENT has child nodes
                if (document.hasChildNodes()) {
                    try {

                        String xmlFirstName = "" ;
                        String xmlLastName = "" ;

                        if(document.getElementsByTagName("FIRST_NAME").item(0) != null) {
                            xmlFirstName = document.getElementsByTagName("FIRST_NAME").
                                    item(0).getTextContent() ;
                        }
                        if (document.getElementsByTagName("SECOND_NAME").item(0) != null) {
                             xmlLastName = document.getElementsByTagName("SECOND_NAME").
                                    item(0).getTextContent() ;
                        }

                        List<String> terms = Collections.singletonList(xmlFirstName+xmlLastName.trim());

                        JaroWinklerSimilarity<String> jws = JaroWinklerSimilarity.with(terms, 0.90);
                        Map<String, Double> similarStrings = jws.apply(firstNameToSearch+lastNameToSearch.trim());

                        if (!similarStrings.isEmpty()) {
                            log.info("terms {} ---- similarity-- {} --request {}" , terms , similarStrings ,  firstNameToSearch+lastNameToSearch.trim());
                            SanctionIndividual resultIndividual = new SanctionIndividual();
                            resultIndividual.setDataId(document.getElementsByTagName("DATAID").item(0).getTextContent());
                            resultIndividual.setVersionNum(document.getElementsByTagName("VERSIONNUM").item(0).getTextContent());
                            resultIndividual.setFirstName(xmlFirstName);
                            resultIndividual.setLastName(xmlLastName);
                            resultIndividual.setUnListType(document.getElementsByTagName("UN_LIST_TYPE").item(0).getTextContent());
                            resultIndividual.setReferenceNumber(document.getElementsByTagName("REFERENCE_NUMBER").item(0).getTextContent());
                            resultIndividual.setListedOn(document.getElementsByTagName("LISTED_ON").item(0).getTextContent());
                            resultIndividual.setComments(document.getElementsByTagName("COMMENTS1").item(0).getTextContent());
                            resultIndividuals.add(resultIndividual);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return resultIndividuals ;

    }
//    private SanctionIndividual searchForName(Document doc, String firstNameToSearch, String lastNameToSearch) {
//        firstNameToSearch="ABDUL LATIF" ;
//        lastNameToSearch="MANSUR" ;
//        NodeList individuals = doc.getDocumentElement().getElementsByTagName("INDIVIDUALS");
//        SanctionIndividual resultIndividual = new SanctionIndividual();
//
//        log.info("the response is ------------- " +individuals.getLength());
//
//        for (int i = 0; i < individuals.getLength(); i++) {
//            Element individual = (Element) individuals.item(i);
//
//            if (i>= 10) {
//                String xmlFirstName = individual.getElementsByTagName("FIRST_NAME").item(0).getTextContent();
//                log.info("breaking at +++++++++++++++ " + xmlFirstName);
//                break;
//            }
//
//                String xmlFirstName = individual.getElementsByTagName("FIRST_NAME").item(0).getTextContent();
//                String xmlLastName = individual.getElementsByTagName("SECOND_NAME").item(0).getTextContent();
//                List<String> terms = Arrays.asList(xmlFirstName, xmlLastName);
//                JaroWinklerSimilarity<String> jws = JaroWinklerSimilarity.with(terms, 0.65);
//                Map<String, Double> similarStrings = jws.apply(firstNameToSearch+lastNameToSearch );
//
//                if (!similarStrings.isEmpty()) {
//                    resultIndividual.setDataId(individual.getElementsByTagName("DATAID").item(0).getTextContent());
//                    resultIndividual.setVersionNum(individual.getElementsByTagName("VERSIONNUM").item(0).getTextContent());
//                    resultIndividual.setFirstName(individual.getElementsByTagName("FIRST_NAME").item(0).getTextContent());
//                    resultIndividual.setLastName(individual.getElementsByTagName("SECOND_NAME").item(0).getTextContent());
//                    resultIndividual.setUnListType(individual.getElementsByTagName("UN_LIST_TYPE").item(0).getTextContent());
//                    resultIndividual.setReferenceNumber(individual.getElementsByTagName("REFERENCE_NUMBER").item(0).getTextContent());
//                    resultIndividual.setListedOn(individual.getElementsByTagName("LISTED_ON").item(0).getTextContent());
//                    resultIndividual.setComments(individual.getElementsByTagName("COMMENTS1").item(0).getTextContent());
//                    log.info(" similarStrings >>>>>>>>>>>>>>>>> " + similarStrings);
//
//                } else {
//                    log.info(" does not match skipping " + similarStrings);
//                    break;
//                }
//
//        }
//        return resultIndividual;
//    }

    private  SanctionIndividual searchForDocumentNumber(Document doc, String documentNumberToSearch) {
        NodeList individuals = doc.getElementsByTagName("INDIVIDUAL");

        for (int i = 0; i < individuals.getLength(); i++) {
            Element individual = (Element) individuals.item(i);
            NodeList documentElements = individual.getElementsByTagName("INDIVIDUAL_DOCUMENT");

            for (int j = 0; j < documentElements.getLength(); j++) {
                Element document = (Element) documentElements.item(j);

                // Check if the INDIVIDUAL_DOCUMENT has child nodes
                if (document.hasChildNodes()) {
                    Element numberElement = (Element) document.getElementsByTagName("NUMBER").
                            item(0);

                    try {
                        String docNumber = numberElement.getTextContent();

                        if (docNumber.equalsIgnoreCase(documentNumberToSearch)) {
                            SanctionIndividual resultIndividual = new SanctionIndividual();
                            resultIndividual.setDataId(individual.getElementsByTagName("DATAID").item(0).getTextContent());
                            resultIndividual.setVersionNum(individual.getElementsByTagName("VERSIONNUM").item(0).getTextContent());
                            resultIndividual.setFirstName(individual.getElementsByTagName("FIRST_NAME").item(0).getTextContent());
                            resultIndividual.setLastName(individual.getElementsByTagName("SECOND_NAME").item(0).getTextContent());
                            resultIndividual.setUnListType(individual.getElementsByTagName("UN_LIST_TYPE").item(0).getTextContent());
                            resultIndividual.setReferenceNumber(individual.getElementsByTagName("REFERENCE_NUMBER").item(0).getTextContent());
                            resultIndividual.setListedOn(individual.getElementsByTagName("LISTED_ON").item(0).getTextContent());
                            resultIndividual.setComments(individual.getElementsByTagName("COMMENTS1").item(0).getTextContent());

                            NodeList designationElements = individual.getElementsByTagName("DESIGNATION");
                            for (int k = 0; k < designationElements.getLength(); k++) {
                                Element designation = (Element) designationElements.item(k);
                                resultIndividual.getDesignations().add(designation.getTextContent());
                            }

                            resultIndividual.setListType(individual.getElementsByTagName("LIST_TYPE").item(0).getTextContent());
                            resultIndividual.setLastDayUpdated(individual.getElementsByTagName("LAST_DAY_UPDATED").item(0).getTextContent());

                            NodeList aliasElements = individual.getElementsByTagName("ALIAS_NAME");
                            for (int k = 0; k < aliasElements.getLength(); k++) {
                                Element alias = (Element) aliasElements.item(k);
                                resultIndividual.getAliases().add(alias.getTextContent());
                            }

                            Element placeOfBirth = (Element) individual.getElementsByTagName("INDIVIDUAL_PLACE_OF_BIRTH").item(0);

                            if(placeOfBirth.hasChildNodes()){
                                NodeList cityElements = placeOfBirth.getElementsByTagName("CITY");
                                resultIndividual.setPlaceOfBirthCity(cityElements.getLength() > 0 ? cityElements.item(0).getTextContent() : "");

                                NodeList countryElements = placeOfBirth.getElementsByTagName("COUNTRY");
                                resultIndividual.setPlaceOfBirthCountry(countryElements.getLength() > 0 ? countryElements.item(0).getTextContent() : "");

                            }

                            // Extract document numbers only if the INDIVIDUAL_DOCUMENT is present
                            NodeList documentNumberElements = document.getElementsByTagName("NUMBER");
                            for (int k = 0; k < documentNumberElements.getLength(); k++) {
                                Element documentNumber = (Element) documentNumberElements.item(k);
                                resultIndividual.getDocumentNumbers().add(documentNumber.getTextContent());
                            }
                            log.info("the response is ------------- " +resultIndividual);
                            return resultIndividual;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return null;
    }

}
