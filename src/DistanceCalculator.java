import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.fc2.web.yamadarake.Coords;

public class DistanceCalculator {
	public static void main(String[] args) {

		try {
			File csv = new File(args[0]); // CSVデータファイル

			BufferedReader br = new BufferedReader(new FileReader(csv));

			double prevLong = 0d;
			double prevLat = 0d;
			
			// 最終行まで読み込む
			String line = "";
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");
				ArrayList<Double> coords = new ArrayList<Double>();
				while (st.hasMoreTokens()) {
					// 1行の各要素をタブ区切りで表示
					String token = st.nextToken();
					System.out.print(token + "\t");
					coords.add(Double.valueOf(token));
				}

				// 距離を表示
				if (prevLat != 0d) {
					double dist = Coords.calcDistHubeny(prevLat, prevLong, coords.get(1), coords.get(0));
					System.out.print("\t" + String.valueOf(dist));
				}
				
				System.out.println();
				
				prevLong = coords.get(0);
				prevLat = coords.get(1);
			}
			br.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
	}
}
