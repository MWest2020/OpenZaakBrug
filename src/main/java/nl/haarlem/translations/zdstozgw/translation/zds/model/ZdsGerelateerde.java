package nl.haarlem.translations.zdstozgw.translation.zds.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import static nl.haarlem.translations.zdstozgw.translation.zds.model.namespace.Namespace.STUF;
import static nl.haarlem.translations.zdstozgw.translation.zds.model.namespace.Namespace.ZKN;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ZdsGerelateerde  extends ZdsObject {

    @XmlAttribute(namespace = STUF)
    public String entiteittype;

    @XmlElement(namespace = ZKN)
    public String identificatie;

    @XmlAttribute(namespace = STUF)
    public String verwerkingssoort;

    @XmlElement(namespace = ZKN, nillable = true)
    public String omschrijving;

    @XmlElement(namespace = ZKN, nillable = true)
    public String code;

    @XmlElement(namespace = ZKN)
    public String ingangsdatumObject;

    @XmlElement(namespace = ZKN, name = "zkt.code")
    public String zktCode;

    @XmlElement(namespace = ZKN, name = "zkt.omschrijving")
    public String zktOmschrijving;

    @XmlElement(namespace = ZKN)
    public String volgnummer;

    @XmlElement(namespace = ZKN)
    public ZdsMedewerker medewerker;

    @XmlElement(namespace = ZKN)
    public ZdsNatuurlijkPersoon natuurlijkPersoon;

    @XmlElement(namespace = ZKN)
    public ZdsVestiging zdsVestiging;
}