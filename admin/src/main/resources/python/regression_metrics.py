import pickle
from sklearn import linear_model
from sklearn.metrics import accuracy_score, mean_squared_error
from sklearn.externals.joblib import dump, load
import argparse
import os
import numpy as np
import pandas as pd
import json


def main(args):
    data_dir = os.path.join(args.root, "data")  # get data dir

    data_path = os.path.join(data_dir, args.inFile)

    metrics_path = os.path.join(args.root, "metrics", args.outFileName)

    print('load data from'+data_path)
    df = pd.read_csv(data_path)
    pred = df['pred'].tolist()
    target = df['target'].tolist()

    eval_result = '*'*8 + 'Evaluation' + '*'*10
    print('Evaluation:')
    print(f'Data Size:{df.size}')
    acc = accuracy_score(pred, target)
    eval_result += '\n' + f'Accuracy:{acc}'
    squa = mean_squared_error(y_test, y_pred)
    eval_result += '\n' + f'mean_squared_error:{squa}'
    print(eval_result)
    if args.txt_report == 'true':
        txt_file = open(metrics_path+'.txt', 'w')
        txt_file.write(eval_result)
        txt_file.close()


if __name__ == '__main__':

    parser = argparse.ArgumentParser()

    parser.add_argument('--inFile', type=str, help='input file path')
    parser.add_argument('--outFileName', type=str, help="output file's name")
    parser.add_argument('--root', type=str, help="file root")

    parser.add_argument('--txt_report', type=str, default='true')

    args = parser.parse_args()
    print(args)

    main(args)