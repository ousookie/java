package test.test.entities;

import javax.persistence.*;

@Entity(name = "Url")
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "bigint")
    private Integer id;
    @Column(name = "src_url", columnDefinition = "varchar", unique = true)
    private String srcUrl;
    @Column(name = "cut_url", columnDefinition = "varchar")
    private String cutUrl;

    public Url() {
    }

    public Url(Integer id, String srcUrl, String cutUrl) {
        this.id = id;
        this.srcUrl = srcUrl;
        this.cutUrl = cutUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getCutUrl() {
        return cutUrl;
    }

    public void setCutUrl(String cutUrl) {
        this.cutUrl = cutUrl;
    }
}
