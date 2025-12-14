package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DocumentVersion implements Comparable<DocumentVersion> {
    int docId;
    int version;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DocumentVersion that = (DocumentVersion) o;
        return docId == that.docId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(docId);
    }

    public DocumentVersion(int docId, int version) {
        this.docId = docId;
        this.version = version;
    }

    @Override
    public int compareTo(@NotNull DocumentVersion o) {
        int r = Integer.compare(this.version, o.version);
    return r;
    }
}
