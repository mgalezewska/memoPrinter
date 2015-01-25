package pl.org.mgalezewska.memo.pdf;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import pl.org.mgalezewska.memo.bo.MemoBO;
import pl.org.mgalezewska.memo.utils.TextUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;

/**
 * User: mgalezewska
 * Date: 2015-01-24
 */
public class FrontTable extends Table {

    public FrontTable(PDPage page, PDPageContentStream contentStream, int rows, int cols) {
        super(page, contentStream, rows, cols);
    }

    @Override
    void drawContent(List<MemoBO> content) throws IOException {
        float textx = 0f;
        float texty = y - (rowHeight/2);

        int cell = cols;
        for (MemoBO memo : content) {
            float stringWidth = TextUtils.getWidthInPixels(memo.getWordPl());
            textx += (colWidth /2) - (stringWidth / 2);
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx, texty);
            contentStream.drawString(memo.getWordPl());
            contentStream.endText();
            textx -= (colWidth /2) - (stringWidth / 2);
            --cell;
            if (cell != 0) {
                textx += colWidth;
            } else {
                texty-=rowHeight;
                textx = margin + cellMargin;
                cell = 3;
            }
        }

    }
}
