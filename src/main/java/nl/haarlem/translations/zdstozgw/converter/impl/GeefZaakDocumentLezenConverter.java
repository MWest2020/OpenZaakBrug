package nl.haarlem.translations.zdstozgw.converter.impl;

import nl.haarlem.translations.zdstozgw.converter.Converter;
import nl.haarlem.translations.zdstozgw.translation.zds.model.*;
import nl.haarlem.translations.zdstozgw.translation.zds.services.ZaakService;
import nl.haarlem.translations.zdstozgw.utils.XmlUtils;

import java.lang.Object;

public class GeefZaakDocumentLezenConverter implements Converter {
    protected String templatePath;

    public GeefZaakDocumentLezenConverter(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public String Convert(ZaakService zaakService, Object object) {
        String result = "";
        try {
            EdcLa01 edcLa01 = zaakService.getZaakDocumentLezen((EdcLv01) object);
            edcLa01 = getEdcLa01WithStuurgegevens((EdcLv01) object, edcLa01);

            edcLa01.stuurgegevens.berichtcode = "La01";

            return XmlUtils.getSOAPMessageFromObject(edcLa01);

        } catch (Exception ex) {
            ex.printStackTrace();
            var f03 = new nl.haarlem.translations.zdstozgw.translation.zds.model.F03();
            f03.setFaultString("Object was not saved");
            f03.setCode("StUF046");
            f03.setOmschrijving("Object niet opgeslagen");
            f03.setDetails(ex.getMessage());
            return f03.getSoapMessageAsString();
        }
    }

    private EdcLa01 getEdcLa01WithStuurgegevens(EdcLv01 object, EdcLa01 edcLa01) {
        edcLa01.stuurgegevens = new Stuurgegevens();
        edcLa01.stuurgegevens.zender = new Zender();
        edcLa01.stuurgegevens.zender.applicatie = object.stuurgegevens.ontvanger.applicatie;
        edcLa01.stuurgegevens.zender.organisatie =  object.stuurgegevens.ontvanger.organisatie;
        edcLa01.stuurgegevens.zender.gebruiker = object.stuurgegevens.ontvanger.organisatie;

        edcLa01.stuurgegevens.ontvanger = new Ontvanger();
        edcLa01.stuurgegevens.ontvanger.applicatie = object.stuurgegevens.zender.applicatie;
        edcLa01.stuurgegevens.ontvanger.organisatie = object.stuurgegevens.zender.organisatie;
        edcLa01.stuurgegevens.ontvanger.gebruiker = object.stuurgegevens.zender.gebruiker;
        return edcLa01;
    }

}
